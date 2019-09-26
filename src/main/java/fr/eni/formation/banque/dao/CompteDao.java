package fr.eni.formation.banque.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Operation;

public interface CompteDao {

	Compte create(String numero, String intitule);
	
	Compte create(String numero, String intitule, double taux);
	
	Compte read(long id);
	
	Stream <Compte> readNumero(String nom);
	
	Compte readNumeroEntier(String nom);
	
	Stream <Compte> readAll();
	
	Operation addOperations(Compte compte, LocalDate date, String intitule, double montant);
}
