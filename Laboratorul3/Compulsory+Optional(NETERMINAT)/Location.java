package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Location extends Coordinate implements Comparable<Location> {
    private String name;
    private String description;
    private String imageHref;
    private Map<Location, Integer> cost = new HashMap();
    public int place;

    public Map<Location, Integer> getCost() {
        return cost;
    }

    public void setCost(Location node, int value) {
        cost.put(node, value);
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Location other) {
        if (other != null && this.name != null) {
            return this.name.compareTo(other.name);
        } else {
            System.out.println("Error: the element introduced is either null or it's name is null!");
            System.exit(-1);
        }
        return -1;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location(String name, String description, String imageHref, double latitude, double longitude) {
        super(longitude, latitude);
        setName(name);
        setDescription(description);
        setImageHref(imageHref);
    }

    public Location() {
    }
}
