package commands;

import handlers.HandleConnection;
import server.ServerTcp;
import user.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SendMessageCommand implements Command{

    private ServerTcp serverTcp;


    public SendMessageCommand(ServerTcp serverTcp) {
        this.serverTcp = serverTcp;
    }

    @Override
    public void execute(String arg, PrintWriter output, BufferedReader input, HandleConnection handle) {
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
                this.serverTcp.getMessages().get(receiver).add("[" + handle.getOnlinePerson().getUsername() + "]" + " " + message);
                //spunem clientului ca am realizat cu success trimiterea
                output.println("Success: message send successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
