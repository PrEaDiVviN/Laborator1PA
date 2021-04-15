package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Player {
    private String name;
    private float rating;
    private Color color;
    PlayerType type;
    private List<List<Token>> status = Collections.synchronizedList(new ArrayList<>());

    public void printBiggestList() {
        int biggest = Integer.MIN_VALUE;
        List<Token> listaDeAfisat = new ArrayList<>();
        for (List<Token> list : status)
            if (biggest < list.size()) {
                biggest = list.size();
                listaDeAfisat = list;
            }
        System.out.println("Player-ul " + this.name + " are lista de tokene maxima de lungimea " + biggest + ".");
        System.out.println("Urmatoarea este lista de tokeni a userului: ");
        for (Token t : listaDeAfisat)
            System.out.print(t.getPair().toString());
        System.out.println();
    }

    public Player(String name, float rating, Color color, PlayerType type) {
        this.name = new String(name);
        this.rating = rating;
        this.color = color;
        this.type = type;
    }

    public List<List<Token>> getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player-ul " + this.name + " cu rating-ul " + this.rating + " are culoarea " + color;
    }
}
