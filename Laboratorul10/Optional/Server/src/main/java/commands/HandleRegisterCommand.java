package commands;

import handlers.HandleConnection;
import server.ServerTcp;
import user.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HandleRegisterCommand implements Command{

    private ServerTcp serverTcp;
    private Socket socket;

    public HandleRegisterCommand(ServerTcp serverTcp, Socket socket) {
        this.serverTcp = serverTcp;
        this.socket = socket;
    }


    @Override
    public void execute(String arg, PrintWriter output, BufferedReader input, HandleConnection handle) {
        output.println("Please enter the username: ");
        String username = null;
        try {
            username = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (serverTcp.getUsers().contains(new Person(username))) {
            output.println("Error: the username exists! Try again!");
        }
        else {
            output.println("Please insert the password: ");
            try {
                String password = input.readLine();
                Person person = new Person(username,password);
                this.serverTcp.getUsers().add(person);
                this.serverTcp.getUserSockets().add(socket);
                this.serverTcp.getMessages().put(person,new ArrayList<>());
                output.println("Success: register successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
