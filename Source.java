package com.company;

public class Source {
    private String name;
    private SourceType type;
    private int supply;

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public SourceType getType() {
        return type;
    }

    private void setType(SourceType type) {
        this.type = type;
    }

    public Source(String name, SourceType type, int supply) {
        setName(name);
        setType(type);
        setSupply(supply);
    }

    @Override
    public String toString() {
        return "Source: " + getType() + " " + getName() + " and has " + getSupply() + " units of commodity.";
    }

}