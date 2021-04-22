package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {

    private static EntityManagerFactory emf;

    private Manager() {
        emf =  Persistence.createEntityManagerFactory("MyApplicationPU");
    }

   public static EntityManager getEntityManager() {
       if(emf == null)
           new Manager();
       return emf.createEntityManager();
   }
}
