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
                this.handleRegister(output,input);
            else if(reply.contains("send"))
            	this.handleSent(output,input);
            else if(reply.contains("read"))
            	this.handleSent(output,input);
            else if(reply.contains("add friend"))
            	this.addFriends(output,input);
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
    	//citim intructiunea de la server
        try {
        	String answer = input.readLine();
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //citim  numele de utilizator de la tastatura
        Scanner keyboard = new Scanner(System.in);
        String reply = keyboard.nextLine();
        //trimitem numele de utilizator la server
        output.println(reply);
        //citim intruciunea de la la server
        String serverAnswer = null;
        try {
        	serverAnswer = input.readLine();
            System.out.println(serverAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //verificam daca raspunsul serverului este o eroare
        if(!serverAnswer.startsWith("Error")) {
        	//citim parola de la tastatura
	        reply = keyboard.nextLine();
	        //trimitem parola catre server
	        output.println(reply);
	        try {
	        	//citim raspunsul de la server
	            System.out.println(input.readLine());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    }
    private void handleRegister(PrintWriter output, BufferedReader input ) {
        //citim intructiunea de la server
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //citim  numele de utilizator de latastatura
        Scanner keyboard = new Scanner(System.in);
        String reply = keyboard.nextLine();
        //trimitem numele de utilizator la server
        output.println(reply);
        //primim raspunsul de la server si il afisam pe ercan
        String answer = null;
        try {
        	answer = input.readLine();
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //verificam daca am primit eroare
	    if(!answer.startsWith("Error")) {
        	//citim parola de la tastatura
      		reply = keyboard.nextLine();
      		//trimitem parola catre server
        	output.println(reply);
        	//primim raspunsul de la server
        	try {
            	System.out.println(input.readLine());
        	} catch (IOException e) {
            	e.printStackTrace();
        	}
    	}
    }

      public void handleSent(PrintWriter output, BufferedReader input) {
        //citim prima instructiune de la server
        String instructiune = null;
        try {
            instructiune = input.readLine();
            System.out.println(instructiune);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //citim username-ul de la tastatura 
        Scanner keyboard = new Scanner(System.in);
        String username = keyboard.nextLine();

        //trimitem username-ul catre server
        output.println(username);
        //citim reply-ul serverului legat de user
        String reply = null;
        try {
            reply = input.readLine();
            System.out.println(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!reply.startsWith("Error")) {
        	//citim de la tastatura mesajul ce trebuie trimis
        	System.out.println("Suntem blocati de tastatura?");
        	String message = keyboard.nextLine();
        	//trimitem mesajul catre server
        	output.println(message);
        	//citim de la server raspunsul
        	try {
        	System.out.println(input.readLine());	
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } 
        
    }

    private void addFriends(PrintWriter output, BufferedReader input) {
    	//citim  mesajele de la server si le afisam pe ecran
        String mesaje = null;
        try {
            mesaje = input.readLine();
            System.out.println(mesaje.replace('&','\n'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     private void handle(PrintWriter output, BufferedReader input) {
     	//citim reply-ul serverului
        String reply = null;
        try {
            reply = input.readLine();
            System.out.println(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

}
