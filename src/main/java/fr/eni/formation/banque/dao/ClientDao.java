package fr.eni.formation.banque.dao;

import java.util.stream.Stream;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;

public interface ClientDao {

	Client create(String nom, String prenom);
	
	Client read(long id);
	
	Client delete(long id);
	
	Stream<Client> readNom(String nom);
	
	Stream<Client> readAll();
	
	void addCompte(Client titulaire, Compte... comptes);
	 
	
}
