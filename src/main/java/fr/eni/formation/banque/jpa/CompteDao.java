package fr.eni.formation.banque.jpa;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Livret;
import fr.eni.formation.banque.Operation;

public class CompteDao implements fr.eni.formation.banque.dao.CompteDao {

	private EntityManager em = JpaUtil.getEntityManager();
	EntityTransaction tx = null;

	@Override
	public Compte create(String numero, String intitule) {
		Compte compte = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			compte = new Compte(numero, intitule);
			em.persist(compte);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}
		return compte;
	}
	
	
	@Override
	public Compte create(String numero, String intitule, double taux) {
		Compte compte = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			compte = new Livret(numero, intitule, taux);
			em.persist(compte);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}
		return compte;
	}

	@Override
	public Compte read(long id) {
		return em.find(Compte.class, id);
	}

	@Override
	public Stream<Compte> readNumero(String numero) {
		String requ = "from Compte as cpt where cpt.numero like :numero";
		TypedQuery<Compte> query = em.createQuery(requ, Compte.class);
		query.setParameter("numero", String.format("%%%s%%", numero));
		return query.getResultStream();
	}

	@Override
	public Compte readNumeroEntier(String numero) {
		String requ = "from Compte as cpt where cpt.numero like :numero";
		TypedQuery<Compte> query = em.createQuery(requ, Compte.class);
		query.setParameter("numero", numero);
		return query.getSingleResult();
	}

	@Override
	public Stream<Compte> readAll() {
		// API Criteria
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Compte> query = builder.createQuery(Compte.class);
		Root<Compte> from = query.from(Compte.class);
		// from.join("operations", Operation.class);
		query.select(from);
		return em.createQuery(query).getResultStream();
		// return em.createQuery("FROM Compte", Compte.class).getResultStream();

	}

	@Override
	public Operation addOperations(Compte compte, LocalDate date, String libelle, double montant) {
		Operation ope = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			ope = new Operation(date, libelle, montant);
			compte.getOperations().add(ope);
			tx.commit();
			em.refresh(compte);
		} catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}
		return ope;
	}



}
