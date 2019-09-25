package fr.eni.formation.banque;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {

	private String nom = "";

	private String prenom = "";
	
	@OneToMany(cascade =CascadeType.ALL, mappedBy="titulaire")
	private List<Compte> comptes = new LinkedList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.Identity
	private long idClient;

	public Client() {

	}

	public Client(String nom, String prenom) {
		super();
		setNom(nom);
		setPrenom(prenom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	
	

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return String.format(" Client %-15s %-15s (%3d - %2d comptes)", getNom(), getPrenom(), getIdClient(), comptes.size());
	}

}
