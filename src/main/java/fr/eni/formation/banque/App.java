package fr.eni.formation.banque;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localhost-mysql-banque");
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Ouverture d'une connexion à la base MySql");
        
        em.close();
        emf.close();
        
    }
}
