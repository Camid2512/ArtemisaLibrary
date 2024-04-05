package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.CPLUSTopic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Esta clase proporciona funcionalidades CRUD (crear, leer, actualizar y
 * eliminar) para objetos
 * 
 * @author Cristhian Diaz
 * @version 1.0
 * @since 28/03/2024
 *
 */

public class CPLUSTopicDAO implements CRUDOperation<CPLUSTopic> {

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
	 * 
	 */
	public CPLUSTopicDAO() {
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
	 * Funcion para crear un registro en la base de datos
	 */
	@Override
	public void create(CPLUSTopic obj) {
		try {
			open();
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
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
	 * Funcion que permite la eliminacion de un registro en la base de datos por su
	 * id
	 */

	@Override
	public boolean delete(long id) {
		open();
		try {
			// Delete
			em.getTransaction().begin();
			em.remove(em.find(CPLUSTopic.class, id));
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return false;

	}

	/**
	 * Funcion que permite la actualizacion de un registro en la base de datos por
	 * su id
	 */

	@Override
	public boolean update(long id, CPLUSTopic obj) {
		// TODO Auto-generated method stub
		open();
		try {
			// Update
			em.getTransaction().begin();
			CPLUSTopic selected = em.find(CPLUSTopic.class, id);

			selected.setId(obj.getId());
			selected.setTopicName(obj.getTopicName());
			selected.setDescription(obj.getDescription());
			selected.setCode(obj.getCode());
			selected.setDifficulty(obj.getDifficulty());
			em.persist(selected);
			em.getTransaction().commit();
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return false;

	}

	/**
	 * Funcion que toma todos los registros de la base de datos y los almacena en un
	 * ArrayList
	 * 
	 * @return ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CPLUSTopic> readAll() {
		// TODO Auto-generated method stub
		open();
		try {
			// usando JPQL
			return (ArrayList<CPLUSTopic>) em.createQuery("select p from CPLUSTopic p").getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return new ArrayList<CPLUSTopic>();

	}

	/**
	 * Funcion que permite buscar un registro en espefico de la base de datos, por
	 * su id
	 */

	@Override
	public CPLUSTopic findOne(long id) {
		// TODO Auto-generated method stub
		open();
		try {
			// Find
			CPLUSTopic selectedCPLUSTopic = em.find(CPLUSTopic.class, id);
			return selectedCPLUSTopic;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return null;

	}

	/**
	 * Funcion que permite contar el numero total de registros en la base de datos
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub

		return readAll().size();

	}

}
