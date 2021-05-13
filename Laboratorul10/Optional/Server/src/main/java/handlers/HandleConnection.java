package handlers;

import commands.*;
import server.ServerTcp;
import user.Person;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HandleConnection extends Thread{
    private Socket socket;
    private ServerTcp serverTcp;
    private Person onlinePerson;

    public HandleConnection(Socket socket, ServerTcp serverTcp) {
        this.socket = socket;
        this.serverTcp = serverTcp;
    }

    public Person getOnlinePerson() {
        return onlinePerson;
    }

    public void setOnlinePerson(Person onlinePerson) {
        this.onlinePerson = onlinePerson;
    }

    @Override
    public void run() {

        boolean continua = true;
        while( continua && serverTcp.isRunning ) {
        	BufferedReader input = null;
        	try {
        	input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	} catch (IOException e) {
                e.printStackTrace();
            }
            //telling the user to insert the comamand
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(socket.getOutputStream(),true);;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.println("Please insert the command: ");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //reading from the user the command
            String request = null;
            try {
                request = input.readLine();
                System.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //handling the command
            System.out.println(request.length());
            if(request.compareTo("exit") == 0)
                continua = false;
            else if(request.compareTo("login") == 0 && (onlinePerson == null || !onlinePerson.isOnline()))  {
                Command command = new HandleLoginCommand(serverTcp);
                command.execute("",writer,input,this);
            }
            else if(request.compareTo("register") == 0 && (onlinePerson == null || !onlinePerson.isOnline())) {
                Command command = new HandleRegisterCommand(serverTcp,socket);
                command.execute("",writer,input, this);
            }
            else if(request.startsWith("add friend ") && onlinePerson!= null) {
                Command command = new AddFriendsCommand(serverTcp);
                command.execute(request, writer, input,this);
            }
            else if(request.startsWith("send") && onlinePerson != null) {
                Command command = new SendMessageCommand(serverTcp);
                command.execute("", writer, input,this);
            }
            else if(request.compareTo("read") == 0 && onlinePerson != null) {
                Command command = new ReadMessageAllCommand(serverTcp);
                command.execute("",writer,input, this);
            }
            else {
                String reply = "Error: Unknown command! Try again!";
                writer.println(reply);
            }
        }
        //the user left, closing it's socket

        try {
            this.serverTcp.getUserSockets().remove(socket);
            socket.close();
            if(onlinePerson != null)
                onlinePerson.setOnline(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
