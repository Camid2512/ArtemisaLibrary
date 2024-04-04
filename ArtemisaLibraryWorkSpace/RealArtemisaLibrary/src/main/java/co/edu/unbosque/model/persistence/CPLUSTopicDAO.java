package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.CPLUSTopic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CPLUSTopicDAO implements CRUDOperation<CPLUSTopic> {
	public EntityManagerFactory emf;
	public EntityManager em;

	public CPLUSTopicDAO() {
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

	@Override
	public int count() {
		// TODO Auto-generated method stub

		return readAll().size();

	}

}
