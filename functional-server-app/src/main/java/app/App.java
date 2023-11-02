package app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.codec.binary.Base64;

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

        // AVIF
        //String avif_lc = new File("html/lc.avif").getAbsolutePath();
        //final String content_avif_lc = Files.readString(Paths.get(avif_lc));


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
        String html_log_in              = new File("html/Log In.html").getAbsolutePath();
        final String content_log_in = Files.readString(Paths.get(html_log_in));
        String html_login_page          = new File("html/login page.html").getAbsolutePath();
        final String content_login_page = Files.readString(Paths.get(html_login_page));

        // JPEG
        //String jpeg_cc4 = new File("html/cc4.jpeg").getAbsolutePath();
        //final String content_jpeg_cc4 = Files.readString(Paths.get(jpeg_cc4));

        // JPG
        /*
        String jpg_ab = new File("html/ab.jpg").getAbsolutePath();
        final String content_jpg_ab = Files.readString(Paths.get(jpg_ab));
        String jpg_ab2 = new File("html/ab2.jpg").getAbsolutePath();
        final String content_jpg_ab2 = Files.readString(Paths.get(jpg_ab2));
        String jpg_abuilding = new File("html/abuilding.jpg").getAbsolutePath();
        final String content_jpg_abuilding = Files.readString(Paths.get(jpg_abuilding));
        String jpg_academicb = new File("html/academicb.jpg").getAbsolutePath();
        final String content_jpg_academicb = Files.readString(Paths.get(jpg_academicb));
        String jpg_cc = new File("html/cc.jpg").getAbsolutePath();
        final String content_jpg_cc = Files.readString(Paths.get(jpg_cc));
        String jpg_cc2 = new File("html/cc2.jpg").getAbsolutePath();
        final String content_jpg_cc2 = Files.readString(Paths.get(jpg_cc2));
        String jpg_cc5 = new File("html/cc5.jpg").getAbsolutePath();
        final String content_jpg_cc5 = Files.readString(Paths.get(jpg_cc5));
        String jpg_ku = new File("html/ku.jpg").getAbsolutePath();
        final String content_jpg_ku = Files.readString(Paths.get(jpg_ku));
        String jpg_ku2 = new File("html/ku2.jpg").getAbsolutePath();
        final String content_jpg_ku2 = Files.readString(Paths.get(jpg_ku2));
        */
        
        String jpg_ku4 = new File("html/ku4.jpg").getAbsolutePath();
        File content_jpg_ku4 = new File(jpg_ku4);
        
        /*
        String jpg_lc = new File("html/LC.jpg").getAbsolutePath();
        final String content_jpg_lc = Files.readString(Paths.get(jpg_lc));
        String jpg_main_page = new File("html/main page.jpg").getAbsolutePath();
        final String content_jpg_main_page = Files.readString(Paths.get(jpg_main_page));
        String jpg_test = new File("html/test.jpg").getAbsolutePath();
        final String content_jpg_test = Files.readString(Paths.get(jpg_test));

        // SVG
        String svg_logo = new File("html/logo.svg").getAbsolutePath();
        final String content_svg_logo = Files.readString(Paths.get(svg_logo));

        // PNG
        String png_head = new File("html/head.png").getAbsolutePath();
        final String content_png_head = Files.readString(Paths.get(png_head));
        */
        String png_logo = new File("html/logo.png").getAbsolutePath();
        Path path = Paths.get(png_logo);
        String content_png_logo = new String(Base64.encodeBase64(Files.readAllBytes(path)), "UTF-8");
        //String encodedfile = "data:image/png;base64," + encodedfile_temp; // Added UTF-8 header
        //System.out.println(encodedfile); // Prints the file as Base64 UTF-8

        /*
        String png_logo1 = new File("html/logo1.png").getAbsolutePath();
        final String content_png_logo1 = Files.readString(Paths.get(png_logo1));
        */
        // TXT
        String txt_log_in = new File("html/log in.txt").getAbsolutePath();
        final String content_txt_log_in = Files.readString(Paths.get(txt_log_in));

        /*
        // WEBP
        String webp_c6 = new File("html/c6.webp").getAbsolutePath();
        final String content_webp_c6 = Files.readString(Paths.get(webp_c6));
        String webp_cc = new File("html/cc.webp").getAbsolutePath();
        final String content_webp_cc = Files.readString(Paths.get(webp_cc));
        */

        InetAddress addr = InetAddress.getByName("127.0.0.1");

        Server myServer = new Server(80, 100, addr);
        myServer.addRoute(GET, "/",
                (req) -> new HttpResponse.Builder()
                        .setStatusCode(200)
                        .addHeader("Content-Type", "text/html")
                        .setEntity(content_index)
                        .build());
        
        myServer.addRoute(POST, "/post",
                (req) -> new HttpResponse.Builder()
                        .setStatusCode(200)
                        .addHeader("Content-Type", "text/html")
                        .setEntity("<HTML> <P> POST REQUEST </P> </HTML>")
                        .build());
        
        // CSS
        myServer.addRoute(GET, "/Academic%20Building.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_academic_building).build());
        myServer.addRoute(GET, "/Campus%20Center.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_campus_center).build());
        myServer.addRoute(GET, "/Learning%20Commons.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_learning_commons).build());
        myServer.addRoute(GET, "/style.css",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/css").setEntity(content_css_style).build());

        // HTML FILES
        myServer.addRoute(GET, "/index.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_index).build());
        myServer.addRoute(GET, "/Campus%20Center.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_campus_center).build());
        myServer.addRoute(GET, "/Academic%20Building.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_academic_building).build());
        myServer.addRoute(GET, "/Learning%20Commons.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_learning_commons).build());
        myServer.addRoute(GET, "/Log%20In.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_log_in).build());
        myServer.addRoute(GET, "/login%20page.html",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "text/html").setEntity(content_login_page).build());

        // PNG
        myServer.addRoute(GET, "/logo.png",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "image/png; charset=UTF-8").setEntity(content_png_logo).build());

        // JPG
        myServer.addRoute(GET, "/ku4.jpg",(req) -> new HttpResponse.Builder().setStatusCode(200).addHeader("Content-Type", "image/jpeg").setEntity(content_jpg_ku4).build());


        myServer.start();
        

    }
}
