package com.example.demo.exceptions;

public class NoPersonException extends Exception{
    public NoPersonException() {
        super("Exception: no person was found! Check your code!");
    }
}
