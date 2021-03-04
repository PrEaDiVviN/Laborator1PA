package com.company;

public class Restaurant extends Location implements Classifiable {
    private int rankClassification;

    @Override
    public int getRankClassification() {
        return rankClassification;
    }

    public void setRankClassification(int rankClassification) {
        this.rankClassification = rankClassification;
    }

    public Restaurant(String name, String description, String imageHref, double latitude, double longitude, int rankClassification) {
        super(name, description, imageHref, latitude, longitude);
        setRankClassification(rankClassification);
    }

    public Restaurant() {
    }
}
