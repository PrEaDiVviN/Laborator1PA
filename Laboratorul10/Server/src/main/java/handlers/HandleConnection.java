package handlers;

import server.ServerTcp;
import user.Person;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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

    public void handleRegister(PrintWriter output, BufferedReader input) {

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

    public void addFriends(String request) {

    }

    public void sendMessage(PrintWriter output, BufferedReader input) {
        //trimitem instructiunea la client
        output.println("Please enter the username of the person you want to send the message to: ");
        //citim numele utilizatorui catre care doreste sa trimita clientul
        String username = null;
        try {
            username = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //verificam ca userul exista
        if (!serverTcp.getUsers().contains(new Person(username))) {
            output.println("Error: the username does not exists! Try again!");
        }
        else {
            //daca exista, trimitem noua instruciune la client
            output.println("Please insert the message: ");
            try {
                //citim mesajul de la client
                String message = input.readLine();
                Person receiver = null;
                //cautam persoana caruia ii va fi atribuit mesajul
                for(Person person: this.serverTcp.getUsers())
                    if(person.getUsername().compareTo(username) == 0)
                        receiver = person;
                //adaugam mesajul la lista lui de mesaje
                this.serverTcp.getMessages().get(receiver).add("[" + onlinePerson.getUsername() + "]" + " " + message);
                //spunem clientului ca am realizat cu success trimiterea
                output.println("Success: message send successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readAllMessages(PrintWriter output, BufferedReader input) {
        //construim un String cu toate mesajele si modificam \n in &
        String mesaj = new String("");
        for(String currentMessage : this.serverTcp.getMessages().get(onlinePerson)) {
            String modify = new String(currentMessage);
            String modified = modify + "&";
            mesaj = mesaj + modified;
        }
        //trimitem string-ul la client
        output.println(mesaj);
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
            else if(request.compareTo("login") == 0 && (onlinePerson == null || !onlinePerson.isOnline()))
                this.handleLogin(writer,input);
            else if(request.compareTo("register") == 0 && (onlinePerson == null || !onlinePerson.isOnline()))
                this.handleRegister(writer,input);
            else if(request.startsWith("add friend: ") && onlinePerson!= null)
                this.addFriends(request);
            else if(request.startsWith("send") && onlinePerson != null)
                this.sendMessage(writer, input);
            else if(request.compareTo("read") == 0 && onlinePerson != null)
                this.readAllMessages(writer, input);
            else {
                String reply = "Error: Unknown command! Try again!";
                writer.println(reply);
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
