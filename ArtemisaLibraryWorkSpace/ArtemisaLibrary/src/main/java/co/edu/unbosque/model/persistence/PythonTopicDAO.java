package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.PythonTopic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PythonTopicDAO implements CRUDOperation<PythonTopic> {
	public EntityManagerFactory emf;
	public EntityManager em;

	public PythonTopicDAO() {
		emf = Persistence.createEntityManagerFactory("default");
		em = emf.createEntityManager();
	}

	public void open() {
		if (!emf.isOpen() || !em.isOpen()) {
			emf = Persistence.createEntityManagerFactory("default");
			em = emf.createEntityManager();
		}
	}

	@Override
	public void create(PythonTopic obj) {
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

	@Override
	public boolean delete(long id) {
		open();
		try {
			// Delete
			em.getTransaction().begin();
			em.remove(em.find(PythonTopic.class, id));
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

	@Override
	public boolean update(long id, PythonTopic obj) {
		// TODO Auto-generated method stub
		open();
		try {
			// Update
			em.getTransaction().begin();
			PythonTopic selected = em.find(PythonTopic.class, id);

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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PythonTopic> readAll() {
		// TODO Auto-generated method stub
		open();
		try {
			// usando JPQL
			return (ArrayList<PythonTopic>) em.createQuery("select p from pythontopics p").getResultList();
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
		return new ArrayList<PythonTopic>();

	}

	@Override
	public PythonTopic findOne(long id) {
		// TODO Auto-generated method stub
		open();
		try {
			// Find
			PythonTopic selectedPythonTopic = em.find(PythonTopic.class, id);
			return selectedPythonTopic;
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

	@Override
	public int count() {
		// TODO Auto-generated method stub

		return readAll().size();

	}

}
