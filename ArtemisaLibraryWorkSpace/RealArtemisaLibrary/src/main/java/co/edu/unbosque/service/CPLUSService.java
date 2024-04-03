package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.CPLUSTopic;
import co.edu.unbosque.model.CPLUSTopicDTO;
import co.edu.unbosque.model.persistence.CPLUSTopicDAO;
import jakarta.annotation.PostConstruct;

public class CPLUSService implements ServiceOperation<CPLUSTopicDTO> {

	private List<CPLUSTopicDTO> topics;
	private CPLUSTopicDAO cPLUSDAO = new CPLUSTopicDAO();

	@PostConstruct
	public void init() {
		cPLUSDAO = new CPLUSTopicDAO();
		topics = new ArrayList<>();
		topics = readAll();
	}

	public CPLUSTopicDTO toDTO(CPLUSTopic topic) {

		CPLUSTopicDTO dto = new CPLUSTopicDTO();
		dto.setId(topic.getId());
		dto.setTopicName(topic.getTopicName());
		dto.setDescription(topic.getDescription());
		dto.setCode(topic.getCode());
		dto.setDifficulty(topic.getDifficulty());
		return dto;

	}

	public CPLUSTopic toEntity(CPLUSTopicDTO topic) {

		CPLUSTopic entity = new CPLUSTopic();
		entity.setId(topic.getId());
		entity.setTopicName(topic.getTopicName());
		entity.setDescription(topic.getDescription());
		entity.setCode(topic.getCode());
		entity.setDifficulty(topic.getDifficulty());
		return entity;

	}

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

	@Override
	public void create(CPLUSTopicDTO obj) {
		// TODO Auto-generated method stub

		cPLUSDAO.create(toEntity(obj));
		topics = readAll();

	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		boolean result = cPLUSDAO.delete(id);
		topics = readAll();
		return result;
	}

	@Override
	public boolean update(long id, CPLUSTopicDTO obj) {
		// TODO Auto-generated method stub
		boolean result = cPLUSDAO.update(id, toEntity(obj));
		topics = readAll();
		return result;
	}

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

	@Override
	public CPLUSTopicDTO findOne(long id) {
		// TODO Auto-generated method stub
		CPLUSTopicDTO find = toDTO(cPLUSDAO.findOne(id));
		return find;
	}

	public List<CPLUSTopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<CPLUSTopicDTO> topics) {
		this.topics = topics;
	}

	public CPLUSTopicDAO getcPLUSDAO() {
		return cPLUSDAO;
	}

	public void setcPLUSDAO(CPLUSTopicDAO cPLUSDAO) {
		this.cPLUSDAO = cPLUSDAO;
	}

}
