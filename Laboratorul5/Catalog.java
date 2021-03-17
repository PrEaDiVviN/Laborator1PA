package com.company;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Item> items = new ArrayList<>();

    public Catalog (String name, String path) {
        this.name = new String(name);
        this.path = new String(path);
    }
    public Catalog(Catalog other) {
        this.name = new String(other.name);
        this.path = new String(other.path);
        this.items.addAll(other.items);
    }

    public Catalog(){ }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item findById(String id) {
        /*
        for (Item item: items) {
            if(item.getId().compareTo(id) == 0)
                return item;
        }
        */
        return items.stream().filter(e -> e.getId().compareTo(id) == 0).findAny().orElse(null);
    }

    public void play(String id) throws IOException, URISyntaxException {
        if(findById(id) == null) {
            System.out.println("Item identifier is not valid!");
            System.exit(-1);
        }
        Item item = findById(id);
        if(item.getLocation().startsWith("http") || item.getLocation().startsWith("https")) {
                URI url = new URI(item.getLocation());
                Desktop.getDesktop().browse(url);
        }
        else {
                File file = new File(item.getLocation());
                Desktop.getDesktop().open(file);
            }
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public void list() {
        System.out.println("Urmatoarele elemente se afla in catalog: ---------------------------------------------");
        items.stream().forEach(System.out::println);
    }
}
