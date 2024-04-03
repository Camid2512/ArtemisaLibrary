package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.PythonTopic;
import co.edu.unbosque.model.PythonTopicDTO;
import co.edu.unbosque.model.persistence.PythonTopicDAO;
import jakarta.annotation.PostConstruct;

public class PythonTopicService implements ServiceOperation<PythonTopicDTO> {

	private List<PythonTopicDTO> topics;
	private PythonTopicDAO pythonDAO = new PythonTopicDAO();

	@PostConstruct
	public void init() {
		pythonDAO = new PythonTopicDAO();
		topics = new ArrayList<>();
		topics = readAll();
	}

	public PythonTopicDTO toDTO(PythonTopic topic) {

		PythonTopicDTO dto = new PythonTopicDTO();
		dto.setId(topic.getId());
		dto.setTopicName(topic.getTopicName());
		dto.setDescription(topic.getDescription());
		dto.setCode(topic.getCode());
		dto.setDifficulty(topic.getDifficulty());
		return dto;

	}

	public PythonTopic toEntity(PythonTopicDTO topic) {

		PythonTopic entity = new PythonTopic();
		entity.setId(topic.getId());
		entity.setTopicName(topic.getTopicName());
		entity.setDescription(topic.getDescription());
		entity.setCode(topic.getCode());
		entity.setDifficulty(topic.getDifficulty());
		return entity;

	}

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

	@Override
	public void create(PythonTopicDTO obj) {
		// TODO Auto-generated method stub

		pythonDAO.create(toEntity(obj));
		topics = readAll();

	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean result = pythonDAO.delete(id);
		topics = readAll();
		return result;
	}

	@Override
	public boolean update(long id, PythonTopicDTO obj) {
		// TODO Auto-generated method stub
		boolean result = pythonDAO.update(id, toEntity(obj));
		topics = readAll();
		return result;
	}

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

	@Override
	public PythonTopicDTO findOne(long id) {
		// TODO Auto-generated method stub
		PythonTopicDTO find = toDTO(pythonDAO.findOne(id));
		return find;
	}

	public List<PythonTopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<PythonTopicDTO> topics) {
		this.topics = topics;
	}

	public PythonTopicDAO getPythonDAO() {
		return pythonDAO;
	}

	public void setPythonDAO(PythonTopicDAO pythonDAO) {
		this.pythonDAO = pythonDAO;
	}

}
