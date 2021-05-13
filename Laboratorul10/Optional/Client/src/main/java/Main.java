import ClientConnection.ClientTcpConnection;
import handlers.ClientHandler;

public class Main {
    public static void main(String[] args) {
        ClientTcpConnection clientTcpConnection = new ClientTcpConnection("127.0.0.1",5777);
        ClientHandler clientHandler = new ClientHandler(clientTcpConnection);
        clientHandler.handleRequest();
    }
}
