package com.company;

import java.time.Duration;
import java.util.Date;

public class Song extends Item{
    private Duration songDuration;
    private Date releaseDate;
    private String madeByArtist;

    public Song(String id, String name, String location,Duration songDuration, Date releaseDate, String madeByArtist) {
        super(id,name,location);
        this.songDuration = songDuration;
        this.releaseDate = releaseDate;
        this.madeByArtist = madeByArtist;
    }

    public Song(String id, String name, String location) {
        super(id,name,location);
    }

    public Duration getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Duration songDuration) {
        this.songDuration = songDuration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMadeByArtist() {
        return madeByArtist;
    }

    public void setMadeByArtist(String madeByArtist) {
        this.madeByArtist = madeByArtist;
    }

    @Override
    public String toString() {
        return "Melodia " + getName() + " a fost scrisa de " + getMadeByArtist() +
                " si lansata la data de " + getReleaseDate() + ". Aceasta dureaza " +
                getSongDuration() + " si este gasita la adresa " + getLocation() +
                ", fiind identificata de id-ul " + getId() + ".";
    }
}
