package server;

import Sockets.SocketTcpServer;
import exceptions.AcceptConnectionException;
import handlers.HandleConnection;
import handlers.HandleConsoleServer;
import user.Person;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerTcp{
    private SocketTcpServer socketTcpServer;
    private List<Person> users = new ArrayList<>();
    private List<Socket> userSockets = new ArrayList<>();
    private Map<Person,List<String>> messages= new HashMap<>();
    public volatile boolean isRunning = true;

    public ServerTcp(int port, int backlog, String ipAddress) throws IOException {
        this.socketTcpServer = new SocketTcpServer(port,backlog,ipAddress);
    }

    public void startServerTcp() throws IOException, AcceptConnectionException {
        HandleConsoleServer threadConsole = new HandleConsoleServer(this);
        threadConsole.start();
        while(this.isRunning) {
            Socket socket = socketTcpServer.acceptConnection();
            new HandleConnection(socket, this).start();
        }
    }
    public List<Person> getUsers() {
        return this.users;
    }

    public List<Socket> getUserSockets() {
        return this.userSockets;
    }

    public Map<Person, List<String>> getMessages() {
        return messages;
    }

    public void setMessages(Map<Person, List<String>> messages) {
        this.messages = messages;
    }
}
