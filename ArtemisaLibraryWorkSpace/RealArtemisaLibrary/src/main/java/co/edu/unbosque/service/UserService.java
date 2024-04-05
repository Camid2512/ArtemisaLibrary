package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.User;
import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.model.persistence.UserDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * La clase UserService proporciona operaciones de servicio para usuarios.
 * se encarga de la creación, lectura, actualización y eliminación de usuarios,
 * así como la autenticación de usuarios y la conversión entre objetos DTO y entidades.
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 31/03/2024
 */
@Named
@ApplicationScoped
public class UserService implements ServiceOperation<UserDTO> {

	/**
	 * Lista de objetos UserDTO que representan los usuarios.
	 */
	private List<UserDTO> userList;
	
	/**
	 * Objeto UserDAO interactua con la capa persistencia de usuarios.
	 */
	private UserDAO uDAO = new UserDAO();
	
	/**
	 * Objeto UserDTO usuarios encontrado durante la autenticacion.
	 */
	private UserDTO posibleUser = new UserDTO();

	/**
	 * Metodo de inicializacion, inicializa la instancia UserDAO y 
	 * carga la lista de usuarios.
	 */
	@PostConstruct
	public void init() {

		uDAO = new UserDAO();
		userList = new ArrayList<>();
		userList = readAll();

	}

	/**
	 * Convierte un objeto User en un objeto UserDTO.
	 * @param user
	 * @return UserDTO
	 */
	public UserDTO toDTO(User user) {

		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setEmail(user.getEmail());

		return dto;

	}

	/**
	 * Convierte un objeto UserDTO en un objeto User.
	 * @param user
	 * @return entity
	 */
	public User toEntity(UserDTO user) {

		User entity = new User();

		entity.setId(user.getId());
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		entity.setEmail(user.getEmail());

		return entity;
	}

	/**
	 * Obtiene una lista de usuarios limitada.
	 * @param size
	 * @return userList
	 */
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

	/**
	 * Realiza la autenticacion de un usuario.
	 * @param username
	 * @param password
	 * @return true si es exitosa, false de lo contrario
	 */
	public boolean login(String username, String password) {
		posibleUser = findUserByUsername(username);

		if (posibleUser != null) {
			String userPassword = posibleUser.getPassword();
			if (userPassword.equals(password) && userPassword != null) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Crea un nuevo usuario en la base de datos
	 * y actualiza la lista de usuarios.
	 */
	public void create(UserDTO obj) {

		uDAO.create(toEntity(obj));
		userList = readAll();

	}

	/**
	 * Lee todos los usuarios en la base de datos.
	 */
	public List<UserDTO> readAll() {
		userList.clear();
		ArrayList<User> entities = uDAO.readAll();
		for (User user : entities) {

			userList.add(toDTO(user));
		}
		return userList;
	}

	/**
	 * Obtine el numero total de usuarios en la lista.
	 * @return userList
	 */
	public int count() {

		return userList.size();

	}

	/**
	 * Busca un usuario en la lista por el nombre de usuario(username)
	 * @param usernameSearch
	 * @return userDTO
	 */
	public UserDTO findUserByUsername(String usernameSearch) {

		for (UserDTO userDTO : userList) {

			if (userDTO.getUsername().equals(usernameSearch)) {
				return userDTO;
			}

		}
		return null;

	}

	/**
	 * Obtiene la lista de usuarios.
	 * @return userList
	 */
	public List<UserDTO> getUser() {
		return userList;
	}

	/**
	 * Verifica si un usuario con el nombre de usuario ya existe en la base de datos.
	 * @param username
	 * @return userExist
	 */
	public boolean userExist(String username) {
		return uDAO.userExist(username);
	}

	/**
	 * Obtiene la lista de usuarios.
	 * @return userList
	 */
	public List<UserDTO> getUserList() {
		return userList;
	}

	/**
	 * Establece la lista de usuarios.
	 * @param userList
	 */
	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

	/**
	 * Obtiene el objeto UserDAO.
	 * @return uDAO
	 */
	public UserDAO getuDAO() {
		return uDAO;
	}

	/**
	 * Establece el objeto setuDAO.
	 * @param uDAO
	 */
	public void setuDAO(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	/**
	 * Elimina un usuario de la base de datos.
	 */
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Actualiza la info de un usuario de la base de datos.
	 */
	@Override
	public boolean update(long id, UserDTO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Busca un usuario por su id en la base de datos.
	 */
	@Override
	public UserDTO findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtiene el posible usuario encontrado en la autenticacion.
	 * @return posibleUser
	 */
	public UserDTO getPosibleUser() {
		return posibleUser;
	}

	/**
	 * Establece el posible usuario encontrado.
	 * @param posibleUser
	 */
	public void setPosibleUser(UserDTO posibleUser) {
		this.posibleUser = posibleUser;
	}

}
