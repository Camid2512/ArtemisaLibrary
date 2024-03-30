package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserDAO {

	public EntityManagerFactory emf;
	public EntityManager em;

	public UserDAO() {
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

	public void create(User obj) {
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
			List<User> users = em.createQuery("SELECT u FROM libraryuser u", User.class).getResultList();
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
