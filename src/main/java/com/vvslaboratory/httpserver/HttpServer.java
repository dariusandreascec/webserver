package com.vvslaboratory.httpserver;

import com.vvslaboratory.httpserver.config.Configuration;
import com.vvslaboratory.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Driver Class for the Http Server
 *
 */

public class HttpServer {
    public static void main(String [] args){

        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept(); // prompts the socket that is listening to a port to accept any connections that arrives

            InputStream inputStream = socket.getInputStream(); // reading something from the socket
            OutputStream outputStream = socket.getOutputStream();

            // TODO we would read

            // TODO we would writing
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my HTTP Server</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10 ASCII

            String response =
                    "HTTP/1.1 200 OK" + CRLF + // Status Line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    "Content-Length: " + html.getBytes().length + CRLF +  // HEADER
                            CRLF +
                            html +
                            CRLF + CRLF ;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
