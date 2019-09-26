package fr.eni.formation.banque.app;

import java.time.LocalDate;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.ClientDao;
import fr.eni.formation.banque.dao.CompteDao;

public class App {
	public static void main(String[] args) {

		ClientDao daoClient = new fr.eni.formation.banque.jpa.ClientDao();
		CompteDao daoCompte = new fr.eni.formation.banque.jpa.CompteDao();
		CompteDao daoLivret = new fr.eni.formation.banque.jpa.CompteDao();

		/*
		 * *****************************
		 *  CREATION DES CLIENTS EN TABLE
		 * *****************************
		 */
		daoClient.create("Jean", "Pierre", "Boston");
		daoClient.create("René", "Denis");
		daoClient.create("Mauvoisin", "Guillaume", "Mont-de-Marsan");
		Client cli1 = daoClient.create("Hallyday", "Johnny");
		Client cli2 = daoClient.read(3L);

		/*
		 * ***************************** 
		 * CREATION DES COMPTES EN TABLE
		 * *****************************
		 */
		daoCompte.create("69874521", "Compte chèque");
		daoCompte.create("87932001", "PEL");
		daoCompte.create("99932877", "Livret A");
		//Création d'un livret
		daoLivret.create("99999999", "Livret A", 0.75);
		Compte cpt1 = daoCompte.create("98741111", "Livret epargne");
		Compte cpt2 = daoCompte.read(1L);
		Compte cpt3 = daoCompte.read(2L);
		Compte cpt4 = daoCompte.read(4L);

		/*
		 * Attribution d'un ou plusieurs comptes à un titulaire
		 */
		daoClient.addCompte(cli1, cpt1);
		daoClient.addCompte(cli2, cpt2, cpt3, cpt4);

		/*
		 * Création des opérations pour certains comptes
		 */
		daoCompte.addOperations(cpt3, LocalDate.now(), "Remboursement Crédit", -600.0);
		daoCompte.addOperations(cpt3, LocalDate.now(), "Remboursement Amigo", 20.0);
		daoCompte.addOperations(cpt3, LocalDate.now(), "TAN", -14.13);
		daoCompte.addOperations(cpt1, LocalDate.now(), "EDF", -104.56);
		daoCompte.addOperations(cpt1, LocalDate.now(), "Restau", -64.87);
		daoCompte.addOperations(cpt1, LocalDate.now(), "CAF", 145.21);

		System.err.println("READ de tous les clients");
		daoClient.readAll().forEach(System.out::println);
		System.err.println("READ de tous les comptes");
		daoCompte.readAll().forEach(System.out::println);

		
		
		
		
		
		
		
		
		
		
		
		
//		Client cli1 = daoClient.read(1L);
//		System.err.println("READ sur client1");
//		System.out.println(cli1);
//
//		Compte cpt1 = daoCompte.read(1L);
//		System.err.println("READ sur compte1");
//		System.out.println(cpt1);
//		System.err.println("READ sur compte2");
//		Compte cpt2 = daoCompte.read(2L);
//		System.out.println(cpt2);
//		
//
//		System.err.println("READ de tous les clients qui s'appellent Jean");
//		daoClient.readNom("Jean").forEach(System.out::println);

//		System.err.println("READ de tous les comptes qui contiennent \"32\" ");
//		daoCompte.readNumero("32").forEach(System.out::println);

//		System.err.println(" READ du compte 87932001");
//		System.out.println(daoCompte.readNumeroEntier("87932001"));
		// daoClient.delete(5L);

	}
}
