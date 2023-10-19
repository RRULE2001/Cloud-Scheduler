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

        String path = new File("html/test.html").getAbsolutePath();

        System.out.println(path);

        final String content = Files.readString(Paths.get(path));

        InetAddress addr = InetAddress.getByName("127.0.0.1");

        Server myServer = new Server(80, 100, addr);
        myServer.addRoute(GET, "/get",
                (req) -> new HttpResponse.Builder()
                        .setStatusCode(200)
                        .addHeader("Content-Type", "text/html")
                        .setEntity(content)
                        .build());
        
        myServer.addRoute(POST, "/post",
                (req) -> new HttpResponse.Builder()
                        .setStatusCode(200)
                        .addHeader("Content-Type", "text/html")
                        .setEntity("<HTML> <P> POST REQUEST </P> </HTML>")
                        .build());
        myServer.start();
    }
}
