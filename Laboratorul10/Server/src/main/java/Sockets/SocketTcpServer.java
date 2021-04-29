package Sockets;

import exceptions.AcceptConnectionException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTcpServer {
    private final ServerSocket serverSocket;
    public SocketTcpServer(int port, int backlog, String ipAddress) throws UnknownHostException, IOException {
        this.serverSocket = new ServerSocket(port,backlog, InetAddress.getByName(ipAddress));
    }

    public Socket acceptConnection() throws AcceptConnectionException {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new AcceptConnectionException();
    }
}
