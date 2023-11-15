package app;

import java.nio.file.Files;
import java.nio.file.Paths;

import Sockets.Server;
import Sockets.pojos.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import static Sockets.contract.HttpMethod.GET;
import static Sockets.contract.HttpMethod.POST;

/**
 * Test functional server library.
 */


public class App {
    public static void main(String[] args) throws IOException {

        // CSS
        String css_academic_building = new File("html/Academic Building.css").getAbsolutePath();
        final String content_css_academic_building = Files.readString(Paths.get(css_academic_building));
        String css_campus_center = new File("html/Campus Center.css").getAbsolutePath();
        final String content_css_campus_center = Files.readString(Paths.get(css_campus_center));
        String css_learning_commons = new File("html/Learning Commons.css").getAbsolutePath();
        final String content_css_learning_commons = Files.readString(Paths.get(css_learning_commons));
        String css_style = new File("html/style.css").getAbsolutePath();
        final String content_css_style = Files.readString(Paths.get(css_style));

        // HTML
        String html_index               = new File("html/index.html").getAbsolutePath();
        final String content_index = Files.readString(Paths.get(html_index));
        String html_campus_center       = new File("html/Campus Center.html").getAbsolutePath();
        final String content_campus_center = Files.readString(Paths.get(html_campus_center));
        String html_academic_building   = new File("html/Academic Building.html").getAbsolutePath();
        final String content_academic_building = Files.readString(Paths.get(html_academic_building));
        String html_learning_commons    = new File("html/Learning Commons.html").getAbsolutePath();
        final String content_learning_commons = Files.readString(Paths.get(html_learning_commons));
        String html_login              = new File("html/login.html").getAbsolutePath();
        final String content_login = Files.readString(Paths.get(html_login));

        InetAddress addr = InetAddress.getByName("127.0.0.1");

        Server myServer = new Server(80, 100, addr);

        // HOME ADDRESS
        myServer.addRoute(GET, "/",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_login).build());
        
        // CSS GET REQUESTS
        myServer.addRoute(GET, "/Academic%20Building.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_academic_building).build());
        myServer.addRoute(GET, "/Campus%20Center.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_campus_center).build());
        myServer.addRoute(GET, "/Learning%20Commons.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_learning_commons).build());
        myServer.addRoute(GET, "/style.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_style).build());

        // HTML GET REQUESTS
        myServer.addRoute(GET, "/index.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_index).build());
        myServer.addRoute(GET, "/Campus%20Center.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_campus_center).build());
        myServer.addRoute(GET, "/Academic%20Building.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_academic_building).build());
        myServer.addRoute(GET, "/Learning%20Commons.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_learning_commons).build());
        myServer.addRoute(GET, "/login.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_login).build());

        // SQL REQUESTS
        myServer.addRoute(POST, "/updateRoomStatus",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/plain").setEntity("Room Status Updated").build());
        myServer.addRoute(GET, "/getRoomStatus",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/plain").setEntity("Room Status Received").build());
        
        
        myServer.start();
        

    }
}
 
