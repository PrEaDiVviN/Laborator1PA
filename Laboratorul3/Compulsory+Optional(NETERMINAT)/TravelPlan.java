package com.company;

import java.util.*;

public class TravelPlan {
    private City city;
    private List<Integer> preferences = new ArrayList<>();

    public List<Integer> getPreferences() {
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

    public static int compare(Integer a, Integer b) {
            return a - b;
        }


    public void getBestTravelPlan() {
        boolean[] vizitat = new boolean[city.getNodes().size()];
        int[] tata = new int[city.getNodes().size()];
        int[] distanta = new int[city.getNodes().size()];
        int bigValue = Integer.MAX_VALUE;
        int start = 0; /* Nodul de start -> hotelul */
        for (int i = 0; i < city.getNodes().size(); i++) {
            distanta[i] = bigValue;
            vizitat[i] = false;
            tata[i] = 0;
        }
        distanta[0] = 0;
        List<Location> nodes = city.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            int min = bigValue;
            int min_index = -1;
            for (int j = 0; j < nodes.size(); j++)
                if (!vizitat[j] && distanta[j] <= min) {
                    min = distanta[j];
                    min_index = j;
                }

            vizitat[min_index] = true;
            for (int j = 0; j < nodes.size(); j++)
                if (!vizitat[j] && nodes.get(min_index).getCost().containsKey(nodes.get(j))
                        && distanta[min_index] != bigValue && (distanta[min_index] +
                        nodes.get(min_index).getCost().get(nodes.get(j)) < distanta[j])) {
                    distanta[j] = distanta[min_index] + nodes.get(min_index).getCost().get(nodes.get(j));
                    tata[j] = min_index;
                }
        }
        System.out.println("Urmatoarele sunt distantele!");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(distanta[i] + " ");
        }
        System.out.println("");
        /*
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println((tata[i] + 1) + " ");
        */
        }
    }
}
