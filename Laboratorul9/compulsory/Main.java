import entity.Movie;
import manager.Manager;
import repository.MovieRepository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.sql.Time;

public class Main {

    public static void main(String[] args) {
        /* insertExample(); */
        /* getByNameExample(); */
        /* getByIdExample(); */

    }

    public static void getByIdExample() {
        EntityManager manager = Manager.getEntityManager();
        MovieRepository movieRepository = new MovieRepository(manager);
        Movie movie = movieRepository.findById(800);
        System.out.println(movie.toString());
    }

    public static void getByNameExample() {
        EntityManager manager = Manager.getEntityManager();
        MovieRepository movieRepository = new MovieRepository(manager);
        Movie movie = movieRepository.findByName("BEN-10");
        System.out.println(movie.toString());
    }

    public static void insertExample() {
        EntityManager manager = Manager.getEntityManager();
        MovieRepository movieRepository = new MovieRepository(manager);
        Movie movie = new Movie(999,"BEN-10", Date.valueOf("2012-04-04"), Time.valueOf("02:02:36"),10);
        movieRepository.create(movie);
        movieRepository.closeEntityManager();
    }

    public static void firstExample() {
        EntityManager manager = Manager.getEntityManager();
        manager.getTransaction().begin();
        Movie movie = new Movie(864,"Spider-man-UltimateE", Date.valueOf("2012-04-04"), Time.valueOf("02:02:36"),10);
        manager.persist(movie);
        manager.getTransaction().commit();
        manager.clear();
        System.out.println("Record Successfully Inserted In The Database");
    }

}

