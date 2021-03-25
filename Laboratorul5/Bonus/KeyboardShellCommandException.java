package com.company;

public class KeyboardShellCommandException extends Exception{
    public KeyboardShellCommandException(Exception e) {
        super("Exception began in the keyboardShellCommand: -->\n" + e.getMessage());
    }
}
