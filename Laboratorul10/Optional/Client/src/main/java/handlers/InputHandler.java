package handlers;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler extends Thread{
    private BufferedReader input;

    public InputHandler(BufferedReader input) {
        this.input = input;
    }

    @Override
    public void run() {
        boolean continua = true;
        while(continua) {
            try {
                String message = input.readLine();
                if(message == null) {
                    System.out.println("[SERVER] Shutdown...");
                    System.out.println("[CLIENT] The server closed. Please try again later!");
                    System.exit(0);
                }
                else
                    System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
