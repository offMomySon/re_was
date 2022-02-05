import config.EntryPointConfig;
import http.HttpRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import message.SimpleMessage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
public class Server {
    private static final String END_OF_LINE = "\r\n";

    private final ServerSocket serverSocket;

    private Server(@NonNull ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static Server create() {
        ServerSocket serverSocket = createServerSocket(EntryPointConfig.getInstance().getPort());
        return new Server(serverSocket);
    }

    private static ServerSocket createServerSocket(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serverSocket;
    }

    public void start() {

        while (true) {
            log.info("Waiting connection... {}");

            try (Socket socket = serverSocket.accept();
                 InputStream inputStream = socket.getInputStream()) {

                String hostAddress = socket.getInetAddress().getHostAddress();
                log.info("New Client Connect! Connected IP : {}, Port : {}}", hostAddress, socket.getPort());

                String target = HttpRequest.create(inputStream).getHttpStartLine().getTarget();
                log.info("Target = {}", target);

                SimpleMessage simpleMessage = new SimpleMessage("Test Simple message");

                sendResponse(simpleMessage.create(), socket);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String createHeader(long contentLength) {
        StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append("HTTP/1.1 200 OK ").append(END_OF_LINE);
        headerBuilder.append("Content-Length : ").append(contentLength).append(END_OF_LINE);
        headerBuilder.append("Content-Type: ").append("text/html").append(END_OF_LINE);
        headerBuilder.append("Date: ").append(new Date()).append(END_OF_LINE);
        headerBuilder.append("Server: jihun server 1.0 ").append(END_OF_LINE);
        headerBuilder.append(END_OF_LINE);

        return headerBuilder.toString();
    }

    private static void sendResponse(String message, Socket socket) {
        StringBuilder responseBuilder = new StringBuilder();

        String httpResponse = responseBuilder
                .append(createHeader(message.length()))
                .append(message)
                .append(END_OF_LINE)
                .toString();

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            bufferedOutputStream.write(httpResponse.getBytes(StandardCharsets.UTF_8));
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
