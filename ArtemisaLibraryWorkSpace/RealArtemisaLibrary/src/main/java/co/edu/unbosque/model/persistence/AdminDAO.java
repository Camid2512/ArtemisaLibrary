package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
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
			em.getTransaction().begin();
			;
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

	@SuppressWarnings("unchecked")
	public ArrayList<Admin> readAll() {
		open();
		try {
			return (ArrayList<Admin>) em.createQuery("select p from Admin p").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return new ArrayList<Admin>();
	}

	public int count() {
		try {
			open();
			List<Admin> users = em.createQuery("SELECT u FROM Admin u", Admin.class).getResultList();
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

	public boolean adminExist(String username) {

		open();
		try {

			List<Admin> admins = em.createQuery("SELECT u FROM Admin u WHERE u.username = :username", Admin.class)
					.setParameter("username", username).getResultList();
			return !admins.isEmpty();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
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
