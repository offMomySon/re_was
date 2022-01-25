import http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server {

    private final ServerSocket serverSocket;

    public Server() {
        this.serverSocket = createServerSocket(4000);
    }

    private static ServerSocket createServerSocket(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
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

                String resourceTarget = HttpRequest.create(inputStream).getHttpStartLine().getResourceTarget();
                log.info("Resource Target = {}", resourceTarget);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
