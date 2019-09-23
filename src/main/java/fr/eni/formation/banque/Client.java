package fr.eni.formation.banque;

public class Client {

	private String nom;
	
	private String prenom;

	
	public Client() {
		
	}
	
	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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

	@Override
	public String toString() {
		return String.format("Client  = [nom= %s, prenom= %s]", nom, prenom);
	}
	
	
	
	
}
