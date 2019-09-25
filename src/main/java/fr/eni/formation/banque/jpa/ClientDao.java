package fr.eni.formation.banque.jpa;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;

public class ClientDao implements fr.eni.formation.banque.dao.ClientDao {

	private EntityManager em = JpaUtil.getEntityManager();
	EntityTransaction tx = null;

	@Override
	public Client create(String nom, String prenom) {
		Client client = null;
		try {
			tx = em.getTransaction();
			tx.begin();

			client = new Client(nom, prenom);
			em.persist(client);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}
		return client;
	}

	@Override
	public Client read(long id) {
		return em.find(Client.class, id);
	}

	@Override
	public Stream<Client> readNom(String nom) {
		String requ = "from Client as cli where cli.nom like :nom";
		TypedQuery<Client> query = em.createQuery(requ, Client.class);
		query.setParameter("nom", String.format("%%%s%%", nom));
		return query.getResultStream();
	}

	@Override
	public Stream<Client> readAll() {
		return em.createQuery("FROM Client", Client.class).getResultStream();
	}

	/**
	 * Méthode de suppression de ligne à partir de l'indetifiant
	 */
	@Override
	public Client delete(long id) {
		try {
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.find(Client.class, id));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}
		return em.find(Client.class, id);
	}

	@Override
	public void addCompte(Client titulaire, Compte... comptes) {
		try {
			tx = em.getTransaction();
			tx.begin();
			Arrays.stream(comptes).forEach(cpt -> cpt.setTitulaire(titulaire));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}

	}

}
