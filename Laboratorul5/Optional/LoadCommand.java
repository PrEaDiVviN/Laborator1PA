package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadCommand extends Command {

    private String path;
    public LoadCommand(String path) {
        this.path = new String(path);
    }

    @Override
    public void applyTo(Catalog target) throws InvalidCatalogException{
        FileInputStream file;
        ObjectInputStream input;
        try {
            file = new FileInputStream(path);
            input = new ObjectInputStream(file);
            Catalog catalog = new Catalog((Catalog) input.readObject());
            target.setItems(catalog.getItems());
            target.setName(catalog.getName());
            target.setPath(catalog.getPath());
        }
        catch (FileNotFoundException | ClassNotFoundException e) {
            throw new InvalidCatalogException(" Reason: " + e.getMessage());
        }
        catch (IOException e) {
            throw new InvalidCatalogException("Reason: " + e.getMessage());
        }
    }
}
