package fr.eni.formation.banque;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compte {

	private String numero;
	
	private String intitule;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) // GenerationType.Identity
	private long idCompte;
	
	public Compte() {
		
	}

	public Compte(String numero, String intitule, long idCompte) {
		super();
		setNumero(numero);
		setIntitule(intitule);
		setIdCompte(idCompte);
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

	@Override
	public String toString() {
		return String.format("Compte [numero= %s, intitule= %s, idCompte= %s]", getNumero(), getIntitule(), getIdCompte());
	}
	
	
	
}
