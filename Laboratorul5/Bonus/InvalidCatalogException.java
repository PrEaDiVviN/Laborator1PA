package com.company;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(String ex) {
        super("Invalid catalog file." + ex);
    }
}
