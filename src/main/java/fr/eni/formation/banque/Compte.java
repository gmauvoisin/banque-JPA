package fr.eni.formation.banque;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Compte {

	@Column(nullable = false, unique = true)
	private String numero;

	private String intitule;

	@Transient
	private double solde;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "idClient")
	private Client titulaire;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.Identity
	private long idCompte;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idCompte")
	public List<Operation> operations = new LinkedList<>();

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

	public Client getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(Client titulaire) {
		
		if(this.titulaire != null) {
			this.titulaire.getComptes().remove(this);
		}
		this.titulaire = titulaire;
		
		if(titulaire != null) {
			this.titulaire.getComptes().add(this);			
		}
	}
	
	
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return String.format("Compte n°%s(%d) : %10.2f - %-20s - %s", getNumero(), getIdCompte(), getSolde(), getIntitule(),
				getTitulaire() != null ? getTitulaire().getNom() : "inconnu");
	}

}
