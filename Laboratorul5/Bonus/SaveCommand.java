package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand extends Command{
    @Override
    public void applyTo(Catalog target) throws InvalidCatalogException {
        ObjectOutputStream oos;
        FileOutputStream file;
        try
        {
            file = new FileOutputStream(target.getPath());
            oos = new ObjectOutputStream(file);
            oos.writeObject(target);
        }
        catch (FileNotFoundException e) {
            throw new InvalidCatalogException(" The file was not found!\n");
        }
        catch (IOException e){
            throw new InvalidCatalogException(" The IO operations could not be done!\n");
        }
    }
}
