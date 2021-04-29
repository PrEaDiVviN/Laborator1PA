package handlers;

import server.ServerTcp;
import user.Person;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class HandleConnection extends Thread{
    private Socket socket;
    private ServerTcp serverTcp;
    private Person onlinePerson;

    public HandleConnection(Socket socket, ServerTcp serverTcp) {
        this.socket = socket;
        this.serverTcp = serverTcp;
    }

    private void handleLogin(PrintWriter output, BufferedReader input) {
        System.out.println("HANDLING LOGIN");

        output.println("Please enter the username:");
        System.out.println("MESAJUL A FOST TRIMIS");
        String username = null;
        try {
            username = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!serverTcp.getUsers().contains(new Person(username))) {
            output.println("Error: the username does not exist! Try again!");
        }
        else {
            output.println("Please insert the password: ");
            try {
                String password = input.readLine();
                for(Person person : serverTcp.getUsers())
                    if(person.getUsername().compareTo(username) == 0)
                        if(person.getPassword().compareTo(password) == 0) {
                            output.println("Success: login successfully!");
                            person.setOnline(true);
                            onlinePerson = person;
                        }
                        else
                            output.println("Error: wrong password!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleRegister() {
        PrintWriter output = null;
        try {
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.println("Please enter the username: ");
        BufferedReader input = null;
        String username = null;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            username = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (serverTcp.getUsers().contains(new Person(username))) {
            output.print("Error: the username exists! Try again!");
        }
        else {
            output.print("Please insert the password: ");
            try {
                String password = input.readLine();
                Person person = new Person(username,password);
                this.serverTcp.getUsers().add(person);
                output.print("Success: register successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addFriends(String request) {

    }

    public void sendMessage(String request) {

    }

    public void readAllMessages() {

    }

    @Override
    public void run() {
        boolean continua = true;
        System.out.println("Am intrat in run");
        while( continua ) {
        	BufferedReader input = null;
        	try {
        	input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	} catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("I am trying - 1");
            //telling the user to insert the comamand
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(socket.getOutputStream(),true);;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("I am trying - 2");
            try {
                writer.println("Please insert the command: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("I am trying - 4");

            //reading from the user the command
            System.out.println("M-am blocat-------------------");
            String request = null;
            try {
               
                System.out.println("M-am blocat");
                request = input.readLine();
                System.out.println("M-am deblocat");
                System.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //handling the command
            System.out.println(request.length());
            if(request.compareTo("exit") == 0)
                continua = false;
            else if(request.compareTo("login") == 0)
                this.handleLogin(writer,input);
            else if(request.compareTo("register") == 0)
                this.handleRegister();
            else if(request.startsWith("add friend: "))
                this.addFriends(request);
            else if(request.startsWith("send"))
                this.sendMessage(request);
            else if(request.compareTo("read") == 0)
                this.readAllMessages();
            else {
                PrintWriter output = null;
                try {
                    output = new PrintWriter(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String reply = "Error: Unknown command! Try again!\n";
                output.println(reply);
            }
        }
        //the user left, closing it's socket
        try {
            this.serverTcp.getUserSockets().remove(socket);
            socket.close();
            onlinePerson.setOnline(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
