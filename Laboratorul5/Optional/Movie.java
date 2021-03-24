package com.company;

import java.time.Duration;
import java.sql.Date;

public class Movie extends Item{
    private Duration movieDuration;
    private Date releaseDate;
    private String boxOffice;
    private float rating;

    public Movie(String id, String name, String location,Duration movieDuration, Date releaseDate, String boxOffice, float rating) {
        super(id,name,location);
        this.movieDuration = movieDuration;
        this.releaseDate = releaseDate;
        this.boxOffice = boxOffice;
        this.rating = rating;
    }

    public Movie(Movie from) {
        super(from.getId(),from.getName(),from.getLocation());
        this.boxOffice = new String(from.getBoxOffice());
        this.rating = from.rating;
        this.releaseDate = Date.valueOf(from.getReleaseDate().toString());
        this.movieDuration = Duration.ofSeconds(from.getMovieDuration());
    }

    public Movie(String id, String name, String location) {
        super(id,name,location);
    }

    public long getMovieDuration() {
        return movieDuration.getSeconds();
    }

    public void setMovieDuration(Duration movieDuration) {
        this.movieDuration = movieDuration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Filmul " + getName() + " are un rating de " + getRating() +
                " stele si a fost lansat la " + getReleaseDate() + ". Finalmente a ajuns la " +
                getBoxOffice() + " valoare a boxOffice-ului. Rating-ul este " + getRating() +
                "si este gasit la locatia " + getLocation() + ", fiind identificat de id-ul " +
                getId() + ".";
    }
}
