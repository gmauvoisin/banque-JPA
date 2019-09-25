package fr.eni.formation.banque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Compte {

	@Column(nullable = false, unique = true)
	private String numero;

	private String intitule;

	@Transient
	private double solde;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.Identity
	private long idCompte;

	public Compte() {

	}

	public Compte(String numero, String intitule) {
		super();
		setNumero(numero);
		setIntitule(intitule);
	}
	
	

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public long getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(long idCompte) {
		this.idCompte = idCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return String.format("Compte [numero= %s, intitule= %s, idCompte= %s]", getNumero(), getIntitule(),
				getIdCompte());
	}

}
