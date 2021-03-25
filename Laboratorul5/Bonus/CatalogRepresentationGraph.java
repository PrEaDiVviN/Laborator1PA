package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogRepresentationGraph {
    private Catalog catalog;
    private Map<Item, List<Item>> representation = new HashMap<>();/* fiecare item cu lista sa de noduri de care este legat */

    public Catalog getCatalog() {
        return catalog;
    }

    public Map<Item, List<Item>> getRepresentation() {
        return representation;
    }

    public void setRepresentation(Map<Item, List<Item>> representation) {
        this.representation = representation;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public CatalogRepresentationGraph(Catalog catalog) {
        this.catalog = catalog;
    }

    public void printMatrix() {
        for (Item itm: catalog.getItems()) {
            System.out.println("Itm-ul cu id-ul " + itm.getId() + " are urmatoarele legaturi: ");
            representation.get(itm).stream().forEach(System.out::println);
        }
    }

    public void generateMatrix() {
        //melodiile vor fi legate daca au aceelasi artist, imaginile daca au acelasi autor, filmele daca au acelasi rating, cartile au acelasi autor
        List<Item> songs = catalog.getItems().stream().filter(e -> e instanceof Song).collect(Collectors.toList());
        List<Item> books = catalog.getItems().stream().filter(e -> e instanceof Book).collect(Collectors.toList());
        List<Item> images = catalog.getItems().stream().filter(e -> e instanceof Image).collect(Collectors.toList());
        List<Item> movies = catalog.getItems().stream().filter(e -> e instanceof Movie).collect(Collectors.toList());

        for (Item itm : songs) {
           Song song = (Song) itm;
           List<Item> listaActuala= new ArrayList<>();
            for (Item itm2 : songs)
                if(itm.getId().compareTo(itm2.getId()) != 0)
                    if(((Song) itm).getMadeByArtist().compareTo(((Song) itm2).getMadeByArtist()) == 0)
                    listaActuala.add(itm2);
            representation.put(itm,listaActuala);
        }

        for (Item itm : books) {
            Book book = (Book) itm;
            List<Item> listaActuala= new ArrayList<>();
            for (Item itm2 : books)
                if(itm.getId().compareTo(itm2.getId()) != 0)
                    if(((Book) itm).getAuthor().compareTo(((Book) itm2).getAuthor()) == 0)
                        listaActuala.add(itm2);
            representation.put(itm,listaActuala);
        }

        for (Item itm : images) {
            Image image = (Image) itm;
            List<Item> listaActuala= new ArrayList<>();
            for (Item itm2 : images)
                if(itm.getId().compareTo(itm2.getId()) != 0)
                    if(((Image) itm).getAuthor().compareTo(((Image) itm2).getAuthor()) == 0)
                        listaActuala.add(itm2);
            representation.put(itm,listaActuala);
        }

        for (Item itm : movies) {
            Movie image = (Movie) itm;
            List<Item> listaActuala= new ArrayList<>();
            for (Item itm2 : movies)
                if(itm.getId().compareTo(itm2.getId()) != 0)
                    if(((Movie) itm).getRating() == ((Movie) itm2).getRating())
                        listaActuala.add(itm2);
            representation.put(itm,listaActuala);
        }
    }
}
