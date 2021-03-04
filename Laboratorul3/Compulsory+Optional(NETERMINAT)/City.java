package com.company;

import java.time.LocalTime;
import java.util.*;

public class City {
    String cityName;
    private List<Location> nodes = new ArrayList<>();

    public String getCityName() {
        return cityName;
    }

    private void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Location> getNodes() {
        return nodes;
    }

    private void setNodes(List<Location> nodes) {
        this.nodes = nodes;
    }

    public void addLocation(Location node) {
        nodes.add(node);
    }

    static class sortByOpeningHour implements Comparator<Location>
    {
        public int compare(Location a, Location b)
        {
            return ((Visitable )a).getOpeningTime().compareTo(((Visitable )b).getOpeningTime()) ;
        }
    }

    public void displayVisitableAndNotPayable() {
        List<Location> printedNodes = new ArrayList<>();
        for (Location location : nodes) {
            if((location instanceof Visitable) && !(location instanceof Payable)) {
                printedNodes.add(location);
            }
        }
        Collections.sort(printedNodes, new sortByOpeningHour());
        for(Location location : printedNodes)
            System.out.println("Sorted element: " + location.getName());
    }

    City(String cityName) {
        this.cityName = cityName;
    }
    City(String cityName, List<Location> nodes) {
        setCityName(cityName);
        setNodes(nodes);
    }
    City(){}
}
