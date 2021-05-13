package commands;

import handlers.HandleConnection;
import server.ServerTcp;
import user.Person;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AddFriendsCommand implements Command{

    private ServerTcp serverTcp;

    public AddFriendsCommand(ServerTcp serverTcp) {
        this.serverTcp = serverTcp;
    }

    @Override
    public void execute(String arg, PrintWriter output, BufferedReader input, HandleConnection handle) {
        //construim un String cu useri ca nu exista
        String notExistingUser = new String("");
        String[] friends = arg.split(" ");
        for(int i = 2; i < friends.length; i++) {
            boolean adaugat = false;
            for (Person user : this.serverTcp.getUsers())
                if(user.getUsername().compareTo(friends[i]) == 0) {
                    adaugat = true;
                    handle.getOnlinePerson().getFriendList().add(friends[i]);
                    break;
                }
            if(!adaugat) {
                notExistingUser = notExistingUser + ", " + friends[i];
            }
        }
        //trimitem mesajul la client
        if(notExistingUser.compareTo("") == 0)
            output.println("Success: All the users were added to your friendlist.");
        else
            output.println("Error: the following users could not be added! " + notExistingUser);
    }
}
