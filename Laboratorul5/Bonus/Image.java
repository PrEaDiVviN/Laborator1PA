package com.company;

import java.sql.Date;

public class Image extends Item{
    private String description;
    private String author;
    private Date dateStamp;
    private int height;
    private int width;

    public Image(String id, String name, String location, String description, String author, Date dateStamp, int height, int width) {
        super(id,name,location);
        this.author = author;
        this.description = description;
        this.dateStamp = dateStamp;
        this.height = height;
        this.width = width;
    }

    public Image(String id, String name, String location, String author) {
        super(id,name,location);
    }

    public Image(Image from) {
        super(from.getId(),from.getName(),from.getLocation());
        this.author = new String(from.getAuthor());
        this.description = new String(from.getDescription());
        this.dateStamp = Date.valueOf(from.getDateStamp().toString());
        this.height = from.getHeight();
        this.width = from.getWidth();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Fotografia " + getName() + " a fost facuta de " + getAuthor() +
                " la data de " + getDateStamp() + ", fiind in formatul " +
                getWidth() + "x" + getHeight() + "px. Descrierea fotografiei este " +
                getDescription() + ". Aceasta este gasita la adresa " + getLocation() +
                ", fiind identificata de id-ul " + getId() + ".";
    }
}
