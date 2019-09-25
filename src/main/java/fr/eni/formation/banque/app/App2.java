package fr.eni.formation.banque.app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Operation;
import fr.eni.formation.banque.jpa.ClientDao;
import fr.eni.formation.banque.jpa.JpaUtil;

public class App2 {
	public static void main(String[] args) {

		System.out.println("Ouverture d'une connexion à la base MySql");

		EntityManager em = JpaUtil.getEntityManager();

		EntityTransaction tx = null;
		
		ClientDao daoClient = new ClientDao();
		// Enregistrement de données en base
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(new Client("Jean", "Paul"));
			em.persist(new Client("Mauvoisin", "Guillaume"));
			em.persist(new Client("Vermont", "Clément"));
			em.persist(new Operation(LocalDate.now(), "Remboursement Fred", -50.0));
			em.persist(new Compte("58967423", "Compte Courant"));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		/*
		 * Recherche de données en base avec le find et mise à jour d'une donnée en
		 * utilisant le setter
		 */
		Client cli1 = null;
		Compte cpt1 = null;

		try {
			cli1 = em.find(Client.class, 1L);
			cpt1 = em.find(Compte.class, 1L);
			System.out.println(cli1.toString());
			System.out.println(cpt1.toString());
			tx = em.getTransaction();
			tx.begin();
			cli1.setNom("Koala");
			em.remove(cpt1);
			tx.commit();
			System.out.println(cli1.toString());
			System.out.println(cpt1.toString());
		} catch (PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
		}

		JpaUtil.close();

	}
}
