import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            ClientHandler client = new ClientHandler(socket, clients);
            clients.add(client);
            new Thread(client).start();
        }
    }
}
