package com.company;

public class InvalidItemIdException extends Exception{
    public InvalidItemIdException(String name) {
        super("Exception: the id introduced for the item with the name: " + name + " was not valid!\nEither the id was found already in the catalog or It has length 0.\n");
    }
}
