package commands;

import handlers.HandleConnection;
import server.ServerTcp;
import user.Person;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ReadMessageAllCommand implements Command{
    private ServerTcp serverTcp;


    public ReadMessageAllCommand(ServerTcp serverTcp) {
        this.serverTcp = serverTcp;
    }

    @Override
    public void execute(String arg, PrintWriter output, BufferedReader input, HandleConnection handle) {
        //construim un String cu toate mesajele si modificam \n in &
        String mesaj = new String("");
        for(String currentMessage : this.serverTcp.getMessages().get(handle.getOnlinePerson())) {
            String modify = new String(currentMessage);
            String modified = modify + "\n";
            mesaj = mesaj + modified;
        }
        //trimitem string-ul la client
        output.println(mesaj);
    }
}
