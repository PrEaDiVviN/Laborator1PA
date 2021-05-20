package com.example.demo.exceptions;

public class NoRelationshipException extends Exception{
    public NoRelationshipException() {
        super("Exception: no relationship was found! Check your code!");
    }
}
