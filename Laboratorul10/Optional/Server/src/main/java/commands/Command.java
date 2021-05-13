package commands;

import handlers.HandleConnection;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface Command {
    public void execute(String arg, PrintWriter output, BufferedReader input, HandleConnection handler);
}
