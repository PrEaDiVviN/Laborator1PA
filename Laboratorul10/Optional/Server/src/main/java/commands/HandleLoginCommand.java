package commands;

import handlers.HandleConnection;
import server.ServerTcp;
import user.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HandleLoginCommand implements Command{
    private ServerTcp serverTcp;

    public HandleLoginCommand(ServerTcp serverTcp) {
        this.serverTcp = serverTcp;
    }

    @Override
    public void execute(String arg, PrintWriter output, BufferedReader input, HandleConnection handle) {
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
                            handle.setOnlinePerson(person);
                        }
                        else
                            output.println("Error: wrong password!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
