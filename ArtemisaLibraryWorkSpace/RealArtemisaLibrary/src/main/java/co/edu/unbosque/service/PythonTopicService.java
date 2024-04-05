package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.PythonTopic;
import co.edu.unbosque.model.PythonTopicDTO;
import co.edu.unbosque.model.persistence.PythonTopicDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * La clase PythonTopicService proporciona operaciones operaciones para temas de Python de la biblioteca,
 * se encarga de la creacion, lectura, actualizacion y eliminacion de los temas
 * tambien la conversion entre objetos DTO y entidades, usa PythonTopicDAO para interactuar
 * con la capa de persistencia.
 * 
 * @author Valentina Lara 
 * @version 1.0
 * @since 31/03/2024
 */
@Named
@ApplicationScoped
public class PythonTopicService implements ServiceOperation<PythonTopicDTO> {

	/**
	 * Lista de objetos PythonTopicDTO que representa los temas de Python.
	 */
	private List<PythonTopicDTO> topics;
	
	/**
	 * Objeto PythonTopicDAO interactua con la capa de persistencia de los temas de Python.
	 */
	private PythonTopicDAO pythonDAO = new PythonTopicDAO();

	/**
	 * Inicializa la instancia PythonTopicDAO y carga la lista de temas.
	 */
	@PostConstruct
	public void init() {
		pythonDAO = new PythonTopicDAO();
		topics = new ArrayList<>();
		topics = readAll();
	}

	/**
	 * Convierte un objeto topic en un objeto PythonTopicDTO.
	 * @param topic
	 * @return PythonTopicDTO
	 */
	public PythonTopicDTO toDTO(PythonTopic topic) {

		PythonTopicDTO dto = new PythonTopicDTO();
		dto.setId(topic.getId());
		dto.setTopicName(topic.getTopicName());
		dto.setDescription(topic.getDescription());
		dto.setCode(topic.getCode());
		dto.setDifficulty(topic.getDifficulty());
		return dto;

	}

	/**
	 * Convierte un objeto PythonTopicDTO en un objeto topic.
	 * @param topic
	 * @return entity
	 */
	public PythonTopic toEntity(PythonTopicDTO topic) {

		PythonTopic entity = new PythonTopic();
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
	public List<PythonTopicDTO> getTopics(int size) {

		if (size > topics.size()) {
			List<PythonTopicDTO> shortenedList = new ArrayList<>();
			for (int i = 0; i < topics.size() - 1; i++) {
				shortenedList.add(topics.get(i));
			}
			return shortenedList;
		} else {
			return new ArrayList<>(topics.subList(0, size));
		}

	}

	/**
	 * Crea un nuevo tema en la base de datos
	 * y actualiza la lista de temas.
	 */
	@Override
	public void create(PythonTopicDTO obj) {
		// TODO Auto-generated method stub

		pythonDAO.create(toEntity(obj));
		topics = readAll();

	}

	/**
	 * Elimina un tema de la base de datos.
	 */
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean result = pythonDAO.delete(id);
		topics = readAll();
		return result;
	}

	/**
	 * Actualiza la info de un tema de la base de datos.
	 */
	@Override
	public boolean update(long id, PythonTopicDTO obj) {
		// TODO Auto-generated method stub
		boolean result = pythonDAO.update(id, toEntity(obj));
		topics = readAll();
		return result;
	}

	/**
	 * Lee todos los temas en la base de datos
	 */
	@Override
	public List<PythonTopicDTO> readAll() {
		// TODO Auto-generated method stub
		topics.clear();
		ArrayList<PythonTopic> entities = pythonDAO.readAll();
		for (PythonTopic cplusTopic : entities) {
			topics.add(toDTO(cplusTopic));
		}

		return topics;
	}

	/**
	 * Busca un tema por su id en la base de datos.
	 */
	@Override
	public PythonTopicDTO findOne(long id) {
		// TODO Auto-generated method stub
		PythonTopicDTO find = toDTO(pythonDAO.findOne(id));
		return find;
	}

	/**
	 * Obtiene la lista de temas.
	 * @return topics
	 */
	public List<PythonTopicDTO> getTopics() {
		return topics;
	}

	/**
	 * Establece la lista de temas.
	 * @param topics
	 */
	public void setTopics(List<PythonTopicDTO> topics) {
		this.topics = topics;
	}

	/**
	 * Obtiene el objeto PythonTopicDAO.
	 * @return pythonDAO
	 */
	public PythonTopicDAO getPythonDAO() {
		return pythonDAO;
	}

	/**
	 * Establece el objeto PythonTopicDAO.
	 * @param pythonDAO
	 */
	public void setPythonDAO(PythonTopicDAO pythonDAO) {
		this.pythonDAO = pythonDAO;
	}

}
