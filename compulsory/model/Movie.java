package compulsory.model;

import java.sql.Time;
import java.time.Duration;
import java.sql.Date;

public class Movie {
    private int id;
    private String title;
    private Date release_date;
    private Time duration;
    private int score;

    public Movie(int id, String title, Date release_date, Time duration, int score) {
        this.id = id;
        this.title = new String(title);
        this.release_date = release_date;
        this.duration =  duration;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
