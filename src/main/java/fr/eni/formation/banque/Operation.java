package fr.eni.formation.banque;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operation {

	/*
	 * ************************ Les attributs ************************
	 */
	private LocalDate date;

	private String libelle;

	@Column(nullable = true)
	private double montant;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.Identity
	private long idOperation;

	/*
	 * ************************ Les constructeurs ************************
	 */
	public Operation() {

	}

	public Operation(LocalDate date, String libelle, double montant) {
		super();
		setDate(date);
		setLibelle(libelle);
		setMontant(montant);
	}

	/*
	 * ************************ Les accesseurs ************************
	 */
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		if (montant < 0.0)
			montant = -montant;
		this.montant = montant;
	}

	public long getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(long idOperation) {
		this.idOperation = idOperation;
	}

	/**
	 * Surcharge du toString
	 */
	@Override
	public String toString() {
		return String.format("Operation [date= %s , libelle= %s, montant = %s , idOperation = %s]", date, getLibelle(),
				getMontant(), idOperation);
	}

}
