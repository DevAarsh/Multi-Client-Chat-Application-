import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler implements Runnable {
    private Socket socket;
    private List<ClientHandler> clients;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                for (ClientHandler client : clients) {
                    client.out.println(message);
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        }
    }
}
