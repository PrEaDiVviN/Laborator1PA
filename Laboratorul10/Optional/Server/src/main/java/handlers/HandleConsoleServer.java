package handlers;

import server.ServerTcp;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class HandleConsoleServer extends Thread{

    private ServerTcp serverTcp;

    public HandleConsoleServer(ServerTcp serverTcp) {
        this.serverTcp = serverTcp;
    }

    @Override
    public void run() {
        boolean continua = true;
        while( continua ) {
            System.out.println("[SERVER]The server is listening for commands: ");
            Scanner keyboard = new Scanner(System.in);
            String reply = keyboard.nextLine();
            if (reply.toLowerCase(Locale.ROOT).compareTo("exit") == 0) {
                serverTcp.isRunning = false;
                continua = false;
            }
            else {
                System.out.println("[SERVER]Unknown command! Please try again!");
            }
        }
        System.out.println("[SERVER]Initializing closing procedure..");
    }
}
