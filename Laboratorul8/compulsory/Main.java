package compulsory;

import compulsory.dao.Genres;
import compulsory.dao.Movies;
import compulsory.exceptions.NoGenreException;
import compulsory.exceptions.NoMovieException;
import compulsory.model.Genre;
import compulsory.model.Movie;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;


public class Main {
    public static  void main(String args[]) {
        /*
        Genre genre = new Genre(0,"Fantasy");
        Genres genres = new Genres();
        try {
            genres.InsertGenre(genre);
        } catch (NoGenreException e) {
            e.printStackTrace();
        }
         */

        /*
        Genres genres = new Genres();
        try {
            Genre result = genres.getGenreById(0);
            System.out.println("Gen: (" + result.getId() + " , " + result.getName() + ")");
        }
        catch (NoGenreException e) {
            e.printStackTrace();
        }
        */
        /*
        Genres genres = new Genres();
        try {
            Genre result = genres.getGenreByTitle("Fantasy");
            System.out.println("Gen: (" + result.getId() + " , " + result.getName() + ")");
        }
        catch (NoGenreException e) {
            e.printStackTrace();
        }
         */
        /*
        Movie movie = new Movie(0,"Spider-man", Date.valueOf("2012-04-04"), Time.valueOf("02:02:36"),7);
        Movies movies = new Movies();
        try {
            movies.InsertMovie(movie);
        }
        catch (NoMovieException e) {
            e.printStackTrace();
        }
         */
        /*
        Movies movies = new Movies();
        try {
            Movie movie = movies.getMovieById(0);
            System.out.println("Movie (" + movie.getTitle() + ", " + movie.getId() + ", " + movie.getRelease_date() + ", " + movie.getDuration() + ", " + movie.getScore() + ")");
        }
        catch (NoMovieException e) {
            e.printStackTrace();

        }
        */
        
        Movies movies = new Movies();
        try {
            Movie movie = movies.getMovieByTitle("Spider-man");
            System.out.println("Movie (" + movie.getTitle() + ", " + movie.getId() + ", " + movie.getRelease_date() + ", " + movie.getDuration() + ", " + movie.getScore() + ")");
        }
        catch (NoMovieException e) {
            e.printStackTrace();

        }
    }
}
