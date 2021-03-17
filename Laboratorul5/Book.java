package com.company;

import java.util.Date;

public class Book extends Item{
    private int pageNumber;
    private float rating;
    private Date releaseDate;
    private String author;

    public Book(String id, String name, String location, int pageNumber, float rating, Date releaseDate, String author) {
        super(id,name,location);
        this.pageNumber = pageNumber;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.author = author;
    }

    public Book(String id, String name, String location) {
        super(id,name,location);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Cartea " + getName() + " a fost scrisa de " + getAuthor() +
                " si lansata la data de " + getReleaseDate() + ". Aceasta are " +
                getPageNumber() + " pagini si a primit un rating de " + getRating() +
                ". Aceasta poate fi gasila la locatia " +  getLocation() +
                ", fiind identificata de id-ul " + getId() + ".";
    }
}
