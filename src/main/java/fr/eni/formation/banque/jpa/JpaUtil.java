package fr.eni.formation.banque.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory emf;

	private static ThreadLocal<EntityManager> thread;

	static {
		emf = Persistence.createEntityManagerFactory("localhost-mysql-banque");
		thread = new ThreadLocal<>();
	}

	public static EntityManager getEntityManager() {
		EntityManager em = thread.get();
		if (em == null) {
			em = emf.createEntityManager();
			thread.set(em);
		}
		return em;
	}

	public static void close() {
		EntityManager em = thread.get();
		if (em != null && em.isOpen()) {
			em.close();
			emf.close();
		}

	}

}
