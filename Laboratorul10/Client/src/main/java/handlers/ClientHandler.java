package handlers;

import ClientConnection.ClientTcpConnection;
import com.sun.net.httpserver.Authenticator;

import java.io.*;
import java.util.Scanner;

public class ClientHandler {
    private ClientTcpConnection clientTcpConnection;
    public ClientHandler(ClientTcpConnection clientTcpConnection) {
        this.clientTcpConnection = clientTcpConnection;
    }
    public void handleRequest() {
        System.out.println("Am stabilit conexiunea");
        PrintWriter output = null;
        try {
            output = new PrintWriter(clientTcpConnection.getCommunicationSocket().getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader input = null;
        try {
            input = new BufferedReader ( new InputStreamReader(clientTcpConnection.getCommunicationSocket().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean continua = true;
        while (continua ) {
            //citim mesajul de la serverl si il afisam pe ecran
            System.out.println("WE ARE WORKING!--");
            String message = null;
            try {
                message = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(message);
            System.out.println("Trebuia sa afisam mesajul deja");
            //citim  comanda de la tastatura
            Scanner keyboard = new Scanner(System.in);
            String reply = keyboard.nextLine();
            //trimitem comanda la server
            try {
                output.write(reply + "\n");
                output.flush();
                System.out.println("Eu am trimis");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            //verificam ce fel de comanda am trimis si ne comportam ca atare
            if(reply.contains("exit"))
                System.exit(0);
            else if(reply.contains("login"))
                this.handleLogin(output,input);
            else if(reply.contains("register"))
                this.handleRegister();
            else {
                try {
                    System.out.println(input.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleLogin(PrintWriter output, BufferedReader input ) {
        System.out.println("LOGIN--->");

        try {
        	String answer = input.readLine();
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("LOGIN--->1");
        //citim  comanda de la tastatura
        Scanner keyboard = new Scanner(System.in);
        String reply = keyboard.nextLine();
        //trimitem comanda la server
        output.println(reply);
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reply = keyboard.nextLine();
        output.println(reply);
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleRegister() {
        PrintWriter output = null;
        try {
            output = new PrintWriter(clientTcpConnection.getCommunicationSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(clientTcpConnection.getCommunicationSocket().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //citim  comanda de la tastatura
        Scanner keyboard = new Scanner(System.in);
        String reply = keyboard.nextLine();
        //trimitem comanda la server
        output.print(reply);
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reply = keyboard.nextLine();
        output.print(reply);
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
