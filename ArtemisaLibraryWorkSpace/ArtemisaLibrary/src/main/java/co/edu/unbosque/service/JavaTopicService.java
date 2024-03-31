package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.JavaTopic;
import co.edu.unbosque.model.JavaTopicDTO;
import co.edu.unbosque.model.persistence.JavaTopicDAO;
import jakarta.annotation.PostConstruct;

public class JavaTopicService implements ServiceOperation<JavaTopicDTO> {

	private List<JavaTopicDTO> topics;
	private JavaTopicDAO javaDAO = new JavaTopicDAO();

	@PostConstruct
	public void init() {
		javaDAO = new JavaTopicDAO();
		topics = new ArrayList<>();
		topics = readAll();
	}

	public JavaTopicDTO toDTO(JavaTopic topic) {

		JavaTopicDTO dto = new JavaTopicDTO();
		dto.setId(topic.getId());
		dto.setTopicName(topic.getTopicName());
		dto.setDescription(topic.getDescription());
		dto.setCode(topic.getCode());
		dto.setDifficulty(topic.getDifficulty());
		return dto;

	}

	public JavaTopic toEntity(JavaTopicDTO topic) {

		JavaTopic entity = new JavaTopic();
		entity.setId(topic.getId());
		entity.setTopicName(topic.getTopicName());
		entity.setDescription(topic.getDescription());
		entity.setCode(topic.getCode());
		entity.setDifficulty(topic.getDifficulty());
		return entity;

	}

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

	@Override
	public void create(JavaTopicDTO obj) {
		// TODO Auto-generated method stub

		javaDAO.create(toEntity(obj));
		topics = readAll();

	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean result = javaDAO.delete(id);
		topics = readAll();
		return result;
	}

	@Override
	public boolean update(long id, JavaTopicDTO obj) {
		// TODO Auto-generated method stub
		boolean result = javaDAO.update(id, toEntity(obj));
		topics = readAll();
		return result;
	}

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

	@Override
	public JavaTopicDTO findOne(long id) {
		// TODO Auto-generated method stub
		JavaTopicDTO find = toDTO(javaDAO.findOne(id));
		return find;
	}

	public List<JavaTopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<JavaTopicDTO> topics) {
		this.topics = topics;
	}

	public JavaTopicDAO getjavaDAO() {
		return javaDAO;
	}

	public void setjavaDAO(JavaTopicDAO javaDAO) {
		this.javaDAO = javaDAO;
	}

}
