package server;

import Sockets.SocketTcpServer;
import exceptions.AcceptConnectionException;
import handlers.HandleConnection;
import user.Person;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTcp{
    private SocketTcpServer socketTcpServer;
    private List<Person> users = new ArrayList<>();
    private List<Socket> userSockets = new ArrayList<>();

    public ServerTcp(int port, int backlog, String ipAddress) throws IOException {
        this.socketTcpServer = new SocketTcpServer(port,backlog,ipAddress);
        Person p = new Person("alex","1234");
        users.add(p);
    }

    public void startServerTcp() throws IOException, AcceptConnectionException {
        while(true) {
            Socket socket = socketTcpServer.acceptConnection();
            System.out.println("Am primit un nou client!");
            new HandleConnection(socket, this).start();
        }
    }
    public List<Person> getUsers() {
        return this.users;
    }

    public List<Socket> getUserSockets() {
        return this.userSockets;
    }
}
