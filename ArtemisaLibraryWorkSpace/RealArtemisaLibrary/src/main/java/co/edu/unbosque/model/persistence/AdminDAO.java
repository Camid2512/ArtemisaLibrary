package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import java.util.List;

import co.edu.unbosque.model.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Esta clase permite realizar operaciones de acceso a datos de Admin en una
 * base de datos
 * 
 * @author Cristhian Diaz
 * @version 1.0
 * @since 28/03/2024
 */
public class AdminDAO {
	/**
	 * Atributo EntityManager permite interactuar con la unidad de persistencia.
	 */
	public EntityManagerFactory emf;

	/**
	 * Atributo EntityManager permite realizar operaciones en la base de datos
	 */
	public EntityManager em;

	/**
	 * Constructor que inicializa EntityManager y EntityManager
	 */
	public AdminDAO() {
		// TODO Auto-generated constructor stub

		emf = Persistence.createEntityManagerFactory("default");
		em = emf.createEntityManager();

	}

	/**
	 * Funcion que abre una conexion a la base de datos
	 */
	public void open() {
		if (!emf.isOpen() || !em.isOpen()) {

			emf = Persistence.createEntityManagerFactory("default");
			em = emf.createEntityManager();

		}
	}

	/**
	 * Funcion que permite crear un registro del admin en la base de datos
	 * 
	 * @param obj
	 */
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

	/**
	 * Funcion que toma todos los registros de la base de datos y los almacena en un
	 * ArrayList
	 * 
	 * @return ArrayList
	 */
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

	/**
	 * Funcion que permite contar el numero total de registros en la base de datos
	 * 
	 * @return numero total de registros en Admin
	 */
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

	/**
	 * Funcion que revisa la base de datos si existe un usuario con el mismo nombre
	 * 
	 * @param username
	 * @return boolean
	 */
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
