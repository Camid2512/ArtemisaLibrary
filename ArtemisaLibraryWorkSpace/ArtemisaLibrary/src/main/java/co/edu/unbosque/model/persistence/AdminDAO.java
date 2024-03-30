package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.model.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AdminDAO {
	public EntityManagerFactory emf;
	public EntityManager em;

	public AdminDAO() {
		// TODO Auto-generated constructor stub

		emf = Persistence.createEntityManagerFactory("default");
		em = emf.createEntityManager();

	}

	public void open() {
		if (!emf.isOpen() || !em.isOpen()) {

			emf = Persistence.createEntityManagerFactory("default");
			em = emf.createEntityManager();

		}
	}

	public void create(Admin obj) {
		// TODO Auto-generated method stub
		try {
			open();
			em.getTransaction();
			em.persist(obj);
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();

		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	public int count() {
		try {
			open();
			List<Admin> users = em.createQuery("SELECT u FROM libraryadmin u", Admin.class).getResultList();
			return users.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

}
