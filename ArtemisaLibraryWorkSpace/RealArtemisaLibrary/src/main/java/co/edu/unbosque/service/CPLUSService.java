package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.CPLUSTopic;
import co.edu.unbosque.model.CPLUSTopicDTO;
import co.edu.unbosque.model.persistence.CPLUSTopicDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * La clase CPLUSService proporciona operaciones para temas de C++ de la biblioteca,
 * se encarga de la creacion, lectura, actualizacion y eliminacion de los temas
 * tambien la conversion entre objetos DTO y entidades, usa CPLUSTopicDAO para interactuar
 * con la capa de persistencia.
 * 
 * @author Gabriella Wakil
 * @version 1.0
 * @since 31/03/2024
 */
@Named
@ApplicationScoped
public class CPLUSService implements ServiceOperation<CPLUSTopicDTO> {

	/**
	 * Lista de objetos CPLUSTopicDTO que representa los temas de C++.
	 */
	private List<CPLUSTopicDTO> topics;
	
	/**
	 * Objeto CPLUSTopicDAO interactua con la capa de persistencia de los temas de C++.
	 */
	private CPLUSTopicDAO cPLUSDAO = new CPLUSTopicDAO();

	/**
	 * Inicializa la instancia CPLUSTopicDAO y carga la lista de temas.
	 */
	@PostConstruct
	public void init() {
		cPLUSDAO = new CPLUSTopicDAO();
		topics = new ArrayList<>();
		topics = readAll();
	}

	/**
	 * Convierte un objeto topic en un objeto CPLUSTopicDTO.
	 * 
	 * @param topic
	 * @return CPLUSTopicDTO
	 */
	public CPLUSTopicDTO toDTO(CPLUSTopic topic) {

		CPLUSTopicDTO dto = new CPLUSTopicDTO();
		dto.setId(topic.getId());
		dto.setTopicName(topic.getTopicName());
		dto.setDescription(topic.getDescription());
		dto.setCode(topic.getCode());
		dto.setDifficulty(topic.getDifficulty());
		return dto;

	}

	/**
	 * Convierte un objeto CPLUSTopicDTO en un objeto topic.
	 * @param topic
	 * @return entity
	 */
	public CPLUSTopic toEntity(CPLUSTopicDTO topic) {

		CPLUSTopic entity = new CPLUSTopic();
		entity.setId(topic.getId());
		entity.setTopicName(topic.getTopicName());
		entity.setDescription(topic.getDescription());
		entity.setCode(topic.getCode());
		entity.setDifficulty(topic.getDifficulty());
		return entity;

	}

	/**
	 * Obtiene una lista de temas limitada.
	 * @param size
	 * @return topics
	 */
	public List<CPLUSTopicDTO> getTopics(int size) {

		if (size > topics.size()) {
			List<CPLUSTopicDTO> shortenedList = new ArrayList<>();
			for (int i = 0; i < topics.size() - 1; i++) {
				shortenedList.add(topics.get(i));
			}
			return shortenedList;
		} else {
			return new ArrayList<>(topics.subList(0, size));
		}

	}

	/*
	 * Crea un nuevo tema en la base de datos
	 * y actualiza la lista de temas.
	 */
	@Override
	public void create(CPLUSTopicDTO obj) {
		// TODO Auto-generated method stub

		cPLUSDAO.create(toEntity(obj));
		topics = readAll();

	}

	/**
	 * Elimina un tema de la base de datos.
	 */
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean result = cPLUSDAO.delete(id);
		topics = readAll();
		return result;
	}

	/**
	 * Actualiza la info de un tema de la base de datos.
	 */
	@Override
	public boolean update(long id, CPLUSTopicDTO obj) {
		// TODO Auto-generated method stub
		boolean result = cPLUSDAO.update(id, toEntity(obj));
		topics = readAll();
		return result;
	}

	/**
	 * Lee todos los temas en la base de datos 
	 */
	@Override
	public List<CPLUSTopicDTO> readAll() {
		// TODO Auto-generated method stub
		topics.clear();
		ArrayList<CPLUSTopic> entities = cPLUSDAO.readAll();
		for (CPLUSTopic cplusTopic : entities) {
			topics.add(toDTO(cplusTopic));
		}

		return topics;
	}

	/**
	 * Busca un tema por su id en la base de datos.
	 */
	@Override
	public CPLUSTopicDTO findOne(long id) {
		// TODO Auto-generated method stub
		CPLUSTopicDTO find = toDTO(cPLUSDAO.findOne(id));
		return find;
	}

	/**
	 * Obtiene la lista de temas.
	 * @return topics
	 */
	public List<CPLUSTopicDTO> getTopics() {
		return topics;
	}

	/**
	 * Establece la lista de temas.
	 * @param topics
	 */
	public void setTopics(List<CPLUSTopicDTO> topics) {
		this.topics = topics;
	}

	/**
	 * Obtiene el objeto CPLUSTopicDAO.
	 * @return cPLUSDAO
	 */
	public CPLUSTopicDAO getcPLUSDAO() {
		return cPLUSDAO;
	}

	/**
	 * Establece el objeto CPLUSTopicDAO.
	 * @param cPLUSDAO
	 */
	public void setcPLUSDAO(CPLUSTopicDAO cPLUSDAO) {
		this.cPLUSDAO = cPLUSDAO;
	}

}