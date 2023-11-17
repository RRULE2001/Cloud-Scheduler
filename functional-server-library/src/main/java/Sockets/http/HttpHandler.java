package Sockets.http;

import Sockets.contract.RequestRunner;
import Sockets.pojos.HttpRequest;
import Sockets.pojos.HttpResponse;
import Sockets.writers.ResponseWriter;

import java.io.*;
import java.util.Map;
import java.util.Optional;

import java.sql.*;
/**
 * Handle HTTP Request Response lifecycle.
 */
public class HttpHandler  {

    private final Map<String, RequestRunner> routes;

    public HttpHandler(final Map<String, RequestRunner> routes) {
        this.routes = routes;
    }
    public void handleConnection(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        
        Optional<HttpRequest> request = HttpDecoder.decode(inputStream);
        request.ifPresentOrElse((r) -> handleRequest(r, bufferedWriter), () -> handleInvalidRequest(bufferedWriter));

        // RRULE URL HANDLING FOR POST REQUESTS
        String DB_URL = "jdbc:mysql://localhost:3306/mydb";
        String USER = "root";
        String PASS = "password";
        String SQL_QUERY = "SELECT * FROM rooms;";
        
        try{
            String path = request.get().getUri().getPath();
            String query = request.get().getUri().getQuery();

            System.out.println("path: " + path);
            System.out.println("query: " + query);
            switch(path){
                case "/getRoomStatus":
                    if(query != null)
                    {
                        String[] params = query.split("&"); // Break query into multiple strings 

                        for (String param : params)  
                        {  
                            String name = param.split("=")[0];  
                            String value = param.split("=")[1];  
                            System.out.println("name: " + name);
                            System.out.println("value: " + value);

                            if(name == "room")
                            {
                                SQL_QUERY = "SELECT inUse FROM rooms WHERE roomName='" + value + "'";;
                                System.out.println("SQL: " + SQL_QUERY);
                            }
                            
                            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery(SQL_QUERY);) {
                                // Extract data from result set
                                System.out.println("Connection Made for /getRoomStatus...");
                                
                                if (rs.next()) {
                                    // Retrieve by column name
                                    System.out.println("SQL Data: " + rs.getInt("inUse"));
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } 
                        } 
                    }
                    break;

                case "/updateRoomStatus":
                        String[] params = query.split("&"); // Break query into multiple strings 

                        for (String param : params)  
                        {  
                            String name = param.split("=")[0];  
                            String value = param.split("=")[1];  
                            System.out.println("name: " + name);
                            System.out.println("value: " + value);

                            SQL_QUERY = "UPDATE rooms SET inUse = " + value + " WHERE roomName = '" + name + "' LIMIT 1;";

                            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                                Statement stmt = conn.createStatement();
                                ) {
                                int rows = stmt.executeUpdate(SQL_QUERY);
                                // Extract data from result set
                                System.out.println("Connection Made for /updateRoomStatus...");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } 
                        } 
                    break;

            }
        }
        catch(Exception ignored){
            //System.out.println("query: no query");
        }
        

        bufferedWriter.close();
        inputStream.close();
    }
    private void handleInvalidRequest(final BufferedWriter bufferedWriter) {
        HttpResponse notFoundResponse = new HttpResponse.Builder().setStatusCode(400).setEntity("Invalid Request...").build();
        ResponseWriter.writeResponse(bufferedWriter, notFoundResponse);
    }

    private void handleRequest(final HttpRequest request, final BufferedWriter bufferedWriter) {
        final String routeKey = request.getHttpMethod().name().concat(request.getUri().getRawPath());

        if (routes.containsKey(routeKey)) {
            ResponseWriter.writeResponse(bufferedWriter, routes.get(routeKey).run(request));
        } else {
            // Not found
            ResponseWriter.writeResponse(bufferedWriter, new HttpResponse.Builder().setStatusCode(404).setEntity("Route Not Found...").build());
        }
    }
}
