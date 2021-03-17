package com.company;

import javax.xml.catalog.CatalogException;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath())))
        {
            oos.writeObject(catalog);
        }
    }
    public static Catalog load(String path) throws IOException, ClassNotFoundException, InvalidCatalogException {
        var file = new FileInputStream(path);
        var input = new ObjectInputStream(file);
        Catalog catalog = (Catalog) input.readObject();
        if(catalog != null) {
            return catalog;
        }
        else
            throw new InvalidCatalogException("->Error");
    }
    public static void view(Item item) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        if(item.getLocation().startsWith("http") || item.getLocation().startsWith("https")) {
            URI url = new URI(item.getLocation());
            desktop.browse(url);
        }
        else {
            File file = new File(item.getLocation());
            desktop.open(file);
        }
    }
}
