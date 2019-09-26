package fr.eni.formation.banque;

import javax.persistence.Embeddable;

//@Entity
@Embeddable
public class Adresse {

	
	/*
	 * ***************
	 * Les attributs
	 * ***************
	 */
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public long idAdresse;

	private String ville;

		
	/**
	 * Constructeur par défaut
	 */
	public Adresse() {

	}

	/**
	 * Constructeur avec 1 seul paramètre
	 * 
	 * @param ville le nom de la ville
	 */
	public Adresse(String ville) {
		super();
		setVille(ville);
	}

	/**
	 * Retourne le nom de la ville
	 * 
	 * @return le nom de la ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Définit le nom de la ville
	 * 
	 * @param ville le nom de la ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

//	/**
//	 * L'Id de l'adresse
//	 * 
//	 * @return l'ID de l'adresse
//	 */
//	public long getIdAdresse() {
//		return idAdresse;
//	}
//
//	/**
//	 * Définit l'Id de l'adresse
//	 * 
//	 * @param idAdresse l'Id de l'adresse
//	 */
//	public void setIdAdresse(long idAdresse) {
//		this.idAdresse = idAdresse;
//	}

	/**
	 * Surcharge du toString
	 */
	@Override
	public String toString() {
		return String.format("Adresse [ville=%s]", getVille());
	}

}
