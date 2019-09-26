package fr.eni.formation.banque;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
//@DiscriminatorValue("Compte")
public class Compte {
	
	// ************************
	/// Les attributs
	// ************************
	@Column(nullable = false, unique = true)
	private String numero;

	private String intitule;

	@Transient
//	@Formula(value = "select ifnull(sum(montant), 0) from operation where ope.idCompte = idCompte")
//	@Formula(value = "select ifnull(sum(montant), 0) from operation where ope.idCompte = idCompte")
	private double solde;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "idClient")
	private Client titulaire;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.Identity
	private long idCompte;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCompte")
	public List<Operation> operations = new LinkedList<>();

	/*
	 * ************************ Les constructeurs ************************
	 */
	public Compte() {

	}

	public Compte(String numero, String intitule) {
		super();
		setNumero(numero);
		setIntitule(intitule);
	}

	/*
	 * ************************ Les accesseurs ************************
	 */
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
	
	@PostLoad
	@PostUpdate
	@PostPersist
	private void majsolde() {
		this.solde = operations.stream()
				.mapToDouble(ope -> ope.getMontant())
				.sum();
	}

	public Client getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(Client titulaire) {

		if (this.titulaire != null) {
			this.titulaire.getComptes().remove(this);
		}
		this.titulaire = titulaire;

		if (titulaire != null) {
			this.titulaire.getComptes().add(this);
		}
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	/**
	 * Surcharge méthode toString
	 */
	@Override
	public String toString() {
		return String.format("Compte n°%s(%d) : %10.2f - %-20s - %s", getNumero(), getIdCompte(), getSolde(),
				getIntitule(), getTitulaire() != null ? getTitulaire().getNom() : "inconnu");
	}

}
