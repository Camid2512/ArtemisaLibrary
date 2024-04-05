package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.JavaTopic;
import co.edu.unbosque.model.JavaTopicDTO;
import co.edu.unbosque.model.persistence.JavaTopicDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * La clase JavaTopicService proporciona operaciones operaciones para temas de Java de la biblioteca,
 * se encarga de la creacion, lectura, actualizacion y eliminacion de los temas
 * tambien la conversion entre objetos DTO y entidades, usa JavaTopicDAO para interactuar
 * con la capa de persistencia.
 * 
 * @author Gabriella Wakil
 * @version 1.0
 * @since 31/03/2024
 */
@Named
@ApplicationScoped
public class JavaTopicService implements ServiceOperation<JavaTopicDTO> {

	/**
	 * Lista de objetos JavaTopicDTO que representa los temas de Java.
	 */
	private List<JavaTopicDTO> topics;
	
	/**
	 * Objeto JavaTopicDAO interactua con la capa de persistencia de los temas de Java.
	 */
	private JavaTopicDAO javaDAO = new JavaTopicDAO();

	/**
	 * Inicializa la instancia JavaTopicDAO y carga la lista de temas.
	 */
	@PostConstruct
	public void init() {
		javaDAO = new JavaTopicDAO();
		topics = new ArrayList<>();
		topics = readAll();
	}

	/**
	 * Convierte un objeto topic en un objeto JavaTopicDTO.
	 * @param topic
	 * @return JavaTopicDTO
	 */
	public JavaTopicDTO toDTO(JavaTopic topic) {

		JavaTopicDTO dto = new JavaTopicDTO();
		dto.setId(topic.getId());
		dto.setTopicName(topic.getTopicName());
		dto.setDescription(topic.getDescription());
		dto.setCode(topic.getCode());
		dto.setDifficulty(topic.getDifficulty());
		return dto;

	}

	/**
	 * Convierte un objeto JavaTopicDTO en un objeto topic.
	 * @param topic
	 * @return entity
	 */
	public JavaTopic toEntity(JavaTopicDTO topic) {

		JavaTopic entity = new JavaTopic();
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
	public List<JavaTopicDTO> getTopics(int size) {

		if (size > topics.size()) {
			List<JavaTopicDTO> shortenedList = new ArrayList<>();
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
	public void create(JavaTopicDTO obj) {
		// TODO Auto-generated method stub

		javaDAO.create(toEntity(obj));
		topics = readAll();

	}

	/**
	 * Elimina un tema de la base de datos.
	 */
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean result = javaDAO.delete(id);
		topics = readAll();
		return result;
	}

	/**
	 * Actualiza la info de un tema de la base de datos.
	 */
	@Override
	public boolean update(long id, JavaTopicDTO obj) {
		// TODO Auto-generated method stub
		boolean result = javaDAO.update(id, toEntity(obj));
		topics = readAll();
		return result;
	}

	/**
	 * Lee todos los temas en la base de datos
	 */
	@Override
	public List<JavaTopicDTO> readAll() {
		// TODO Auto-generated method stub
		topics.clear();
		ArrayList<JavaTopic> entities = javaDAO.readAll();
		for (JavaTopic cplusTopic : entities) {
			topics.add(toDTO(cplusTopic));
		}

		return topics;
	}

	/**
	 * Busca un tema por su id en la base de datos.
	 */
	@Override
	public JavaTopicDTO findOne(long id) {
		// TODO Auto-generated method stub
		JavaTopicDTO find = toDTO(javaDAO.findOne(id));
		return find;
	}

	/**
	 * Obtiene la lista de temas.
	 * @return topics
	 */
	public List<JavaTopicDTO> getTopics() {
		return topics;
	}

	/**
	 * Establece la lista de temas.
	 * @param topics
	 */
	public void setTopics(List<JavaTopicDTO> topics) {
		this.topics = topics;
	}

	/**
	 * Obtiene el objeto JavaTopicDAO.
	 * @return javaDAO
	 */
	public JavaTopicDAO getjavaDAO() {
		return javaDAO;
	}

	/**
	 * Establece el objeto JavaTopicDAO.
	 * @param javaDAO
	 */
	public void setjavaDAO(JavaTopicDAO javaDAO) {
		this.javaDAO =javaDAO;
	}

}
