package com.company;

import java.util.*;

public class TravelPlan {
    private City city;
    private ArrayList<Integer> preferences = new ArrayList<>();

    public ArrayList<Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<Integer> preferences) {
        this.preferences = preferences;
    }

    public void addPreference(int preference) {
        preferences.add(preference);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public TravelPlan(City city) {
        this.city = city;
    }

    static class sortByTimeAndPreferences implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    public void getBestTravelPlan() {
        ArrayList<Boolean> vizitat = new ArrayList<>();
        ArrayList<Integer> tata = new ArrayList<>();
        ArrayList<Integer> distanta = new ArrayList<>();
        int bigValue = Integer.MAX_VALUE;
        int start = 0; /* Nodul de start -> hotelul */
        PriorityQueue<Integer> queue = new PriorityQueue(preferences.size(), new sortByTimeAndPreferences());
        for (int i = 0; i < preferences.size(); i++) {
            distanta.add(bigValue);
            vizitat.add(false);
            tata.add(0);
        }

    }
}
