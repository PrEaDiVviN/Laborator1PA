package com.company;

public class Destination {
    private String name;
    private DestinationType type;
    private int demand;

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public DestinationType getType() {
        return type;
    }

    private void setType(DestinationType type) {
        this.type = type;
    }

    public Destination(String name, DestinationType type, int demand) {
        setName(name);
        setType(type);
        setDemand(demand);
    }

    @Override
    public String toString() {
        return "Destination: " + getType() + " " + getName() + " and requires " + getDemand() + " units of commodity.";
    }
}
