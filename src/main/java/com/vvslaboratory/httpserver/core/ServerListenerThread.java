package com.vvslaboratory.httpserver.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListenerThread extends Thread {


    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {

        try {

            Socket socket = serverSocket.accept(); // prompts the socket that is listening to a port to accept any connections that arrives

            LOGGER.info(" * Connection accepted: " + socket.getInetAddress() );

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
                            CRLF + CRLF;

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
