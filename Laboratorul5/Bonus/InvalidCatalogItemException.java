package com.company;

public class InvalidCatalogItemException extends Exception{
    public InvalidCatalogItemException() {
        super("Exception: The item introduced is not one of the types accepted in the list of the Catalog.\n");
    }
}
