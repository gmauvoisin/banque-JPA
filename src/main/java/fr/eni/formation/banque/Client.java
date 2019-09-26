package fr.eni.formation.banque;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {

	/*
	 * ************** Les attributs **************
	 */
	private String nom = "";

	private String prenom = "";

	//@OneToOne(cascade=CascadeType.ALL)
	@Embedded
	private Adresse adresse;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "titulaire")
	private List<Compte> comptes = new LinkedList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.Identity
	private long idClient;

	/**
	 * ***************** Les Constructeurs *****************
	 */
	public Client() {

	}

	public Client(String nom, String prenom) {
		setNom(nom);
		setPrenom(prenom);
	}

	public Client(String nom, String prenom, String ville) {
		setNom(nom);
		setPrenom(prenom);
		setAdresse(new Adresse(ville));
	}

	/**
	 * ************** Les accesseurs **************
	 */

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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	/**
	 * Surcharge toString
	 */
	@Override
	public String toString() {
		return String.format(" Client %-15s %-15s (%3d - %2d comptes)", getNom(), getPrenom(), getIdClient(),
				comptes.size());
	}

}
