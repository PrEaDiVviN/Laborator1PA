package entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.*;


@Table(name = "MOVIES")
@NamedQueries({
        @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id=:id"),
        @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.titlu=:titlu")
        })
@Entity
public class Movie implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;

    @Basic(optional = false)
    @Column(name = "titlu")
    private String titlu;

    @Basic(optional = false)
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;

    @Basic(optional = false)
    @Column(name = "duration")
    @Temporal(TemporalType.TIME)
    private Time duration;

    @Column(name = "score")
    private int score;

    public Movie() {}

    public Movie(int id, String titlu, Date release_date, Time duration, int score) {
        this.id = id;
        this.titlu = new String(titlu);
        this.release_date = release_date;
        this.duration =  duration;
        this.score = score;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", release_date=" + release_date +
                ", duration=" + duration +
                ", score=" + score +
                '}';
    }
}
