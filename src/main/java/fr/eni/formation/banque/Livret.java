package fr.eni.formation.banque;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
//@DiscriminatorValue("Livret") // SINGLE_TABLE
@PrimaryKeyJoinColumn(name="idCompte") // JOINED
public class Livret extends Compte {

	private double taux;

	public Livret() {
		super();
	}

	public Livret(String numero, String intitule, double taux) {
		super(numero, intitule);
		setTaux(taux);
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	@Override
	public String toString() {
		return String.format(super.toString() + " - %.2f%%", taux);
	}
	

}
