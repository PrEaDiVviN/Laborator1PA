package com.company;

public class EmptyCatalogException extends Exception {
    public EmptyCatalogException(String name, String path) {
        super("Exception: The catalog has no data inside it!\nCatalog name: " + name + " Catalog path: " + path + ".\n");
    }
}
