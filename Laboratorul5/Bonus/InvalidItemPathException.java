package com.company;

public class InvalidItemPathException extends Exception{
    public InvalidItemPathException(String path) {
        super("Exception: Invalid path exception.\n Either the path is null or it does not contain any // caracter!\n" + "The path: " + path );
    }
}
