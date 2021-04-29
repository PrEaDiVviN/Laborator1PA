package ClientConnection;

import java.io.IOException;
import java.net.Socket;

public class ClientTcpConnection {
    private String serverAddress;
    private int Port;
    private Socket communicationSocket;
    public ClientTcpConnection(String serverAddress, int Port) {
        this.serverAddress = serverAddress;
        this.Port = Port;
        try {
            communicationSocket = new Socket(serverAddress,Port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getCommunicationSocket() {
        return communicationSocket;
    }

    public void setCommunicationSocket(Socket communicationSocket) {
        this.communicationSocket = communicationSocket;
    }
}
