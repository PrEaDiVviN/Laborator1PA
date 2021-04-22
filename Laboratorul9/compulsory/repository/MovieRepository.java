package repository;

import entity.Movie;
import manager.Manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.sql.Time;

public class MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Movie movie) {
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        entityManager.clear();
        System.out.println("Record Successfully Inserted In The Database");
    }

    public Movie findById(int id) {
       return (Movie)entityManager.createNamedQuery(
                "Movie.findById")
               .setParameter("id",id)
                .getSingleResult();
    }

    public Movie findByName(String name) {
        return (Movie)entityManager.createNamedQuery(
                "Movie.findByTitle")
                .setParameter("titlu",name)
                .getSingleResult();
    }

    public void closeEntityManager() {
        entityManager.close();
    }
}
