package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        for (Item itm : other.items) {
            if(itm instanceof Book) {
                Book obj = new Book((Book) itm);
                items.add(obj);
            }
            else if(itm instanceof Song) {
                Song obj = new Song((Song) itm);
                items.add(obj);
            }
            else if(itm instanceof Image) {
                Image obj = new Image((Image) itm);
                items.add(obj);
            }
            else  if(itm instanceof Movie) {
                Movie obj = new Movie((Movie) itm);
                items.add(obj);
            }
        }
    }
    public Catalog(){ }

    public Item findById(String id) {

        for (Item item: items) {
            if(item.getId().compareTo(id) == 0)
                return item;
        }
        return null;
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
}
