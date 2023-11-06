package Sockets.http;

import Sockets.contract.RequestRunner;
import Sockets.pojos.HttpRequest;
import Sockets.pojos.HttpResponse;
import Sockets.writers.ResponseWriter;

import java.io.*;
import java.util.Map;
import java.util.Optional;

/**
 * Handle HTTP Request Response lifecycle.
 */
public class HttpHandler {

    private final Map<String, RequestRunner> routes;

    public HttpHandler(final Map<String, RequestRunner> routes) {
        this.routes = routes;
    }
    public void handleConnection(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        
        Optional<HttpRequest> request = HttpDecoder.decode(inputStream);
        request.ifPresentOrElse((r) -> handleRequest(r, bufferedWriter), () -> handleInvalidRequest(bufferedWriter));
        
        // RRULE URL HANDLING FOR POST REQUESTS
        try{
            String query = request.get().getUri().getQuery();
            System.out.println("query: " + query);
        }
        catch(Exception ignored){
            System.out.println("query: error");
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
