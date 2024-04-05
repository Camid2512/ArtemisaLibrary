package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.JavaTopic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Esta clase proporciona funcionalidades CRUD (crear, leer, actualizar y
 * eliminar) para objetos
 * 
 * @author Santiago Rueda
 * @version 1.0
 * @since 28/03/2024
 *
 */
public class JavaTopicDAO implements CRUDOperation<JavaTopic> {
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
	public JavaTopicDAO() {
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
	 * 
	 * @param obj
	 */
	@Override
	public void create(JavaTopic obj) {
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
	 * Funcion para eliminar un registro en la base de datos, con su id
	 * 
	 * @param id
	 * @return true or false
	 */
	@Override
	public boolean delete(long id) {
		open();
		try {
			// Delete
			em.getTransaction().begin();
			em.remove(em.find(JavaTopic.class, id));
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
	 * Funcion para actualizar un registro en la base de datos, con su id
	 * 
	 * @param id
	 * @param obj
	 * @return true or false
	 */
	@Override
	public boolean update(long id, JavaTopic obj) {
		// TODO Auto-generated method stub
		open();
		try {
			// Update
			em.getTransaction().begin();
			JavaTopic selected = em.find(JavaTopic.class, id);

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
	 * Funcion que toma todos los registros de la case de datos y los almacena en un
	 * ArrayList
	 * 
	 * @return ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JavaTopic> readAll() {
		// TODO Auto-generated method stub
		open();
		try {
			// usando JPQL
			return (ArrayList<JavaTopic>) em.createQuery("select p from JavaTopic p").getResultList();
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
		return new ArrayList<JavaTopic>();

	}

	/**
	 * Funcion para buscar un registro en especifico de la base detos, por su id
	 * 
	 * @param id
	 * @return ArrayList
	 */
	@Override
	public JavaTopic findOne(long id) {
		// TODO Auto-generated method stub
		open();
		try {
			// Find
			JavaTopic selectedJavaTopic = em.find(JavaTopic.class, id);
			return selectedJavaTopic;
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
