package com.company;

public class Hotel extends Location implements Classifiable {
    private int rankClassification;

    public int getRankClassification() {
        return rankClassification;
    }

    public void setRankClassification(int rankClassification) {
        this.rankClassification = rankClassification;
    }

    Hotel(String name, String description, String imageHref, double latitude, double longitude, int rankClassification) {
         super(name,description,imageHref,latitude,longitude);
         setRankClassification(rankClassification);
    }
    Hotel(){}
}
