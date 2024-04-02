package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.User;
import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.model.persistence.UserDAO;
import jakarta.annotation.PostConstruct;

public class UserService implements ServiceOperation<UserDTO> {

	private List<UserDTO> userList;
	private UserDAO uDAO = new UserDAO();

	@PostConstruct
	public void init() {

		uDAO = new UserDAO();
		userList = new ArrayList<>();
		userList = readAll();

	}

	public UserDTO toDTO(User user) {

		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setEmail(user.getEmail());

		return dto;

	}

	public User toEntity(UserDTO user) {

		User entity = new User();

		entity.setId(user.getId());
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		entity.setEmail(user.getEmail());

		return entity;
	}

	public List<UserDTO> getUsers(int size) {
		if (size > userList.size()) {
			List<UserDTO> shortenedList = new ArrayList<>();
			for (int i = 0; i < userList.size() - 1; i++) {
				shortenedList.add(userList.get(i));
			}
			return shortenedList;
		} else {
			return new ArrayList<>(userList.subList(0, size));
		}
	}

	public void create(UserDTO obj) {

		uDAO.create(toEntity(obj));
		userList = readAll();

	}

	public List<UserDTO> readAll() {
		userList.clear();
		ArrayList<User> entities = uDAO.readAll();
		for (User user : entities) {

			userList.add(toDTO(user));
		}
		return userList;
	}

	public int count() {

		return userList.size();

	}

	public boolean userExist(String username) {
		return uDAO.userExist(username);
	}

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

	public UserDAO getuDAO() {
		return uDAO;
	}

	public void setuDAO(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(long id, UserDTO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDTO findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
