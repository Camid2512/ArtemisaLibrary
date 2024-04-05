package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.persistence.AdminDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * La clase AdminService proporciona operaciones de servicio para administradores.
 * se encarga de la creación, lectura, actualización y eliminación de administradores,
 * así como la autenticación de usuarios y la conversión entre objetos DTO y entidades.
 * 
 * @author Gabriella Wakil
 * @version 1.0
 * @since 31/03/2024
 */
@Named
@ApplicationScoped
public class AdminService implements ServiceOperation<AdminDTO> {

	/**
	 * Lista de objetos AdminDTO que representan los administradores.
	 */
	private List<AdminDTO> adminList;
	
	/**
	 * Objeto AdminDAO interactua con la capa persistencia de administradores.
	 */
	private AdminDAO aDAO = new AdminDAO();
	
	/**
	 * Objeto AdminDTO administrador encontrado durante la autenticacion.
	 */
	private AdminDTO posibleAdmin = new AdminDTO();

	/**
	 * Metodo de inicializacion, inicializa la instancia AdminDAO y 
	 * carga la lista de administradores.
	 */
	@PostConstruct
	public void init() {

		aDAO = new AdminDAO();
		adminList = new ArrayList<>();
		adminList = readAll();

	}

	/**
	 * Convierte un objeto Admin en un objeto AdminDTO.
	 * 
	 * @param admin
	 * @return AdminDTO
	 */
	public AdminDTO toDTO(Admin admin) {

		AdminDTO dto = new AdminDTO();
		dto.setId(admin.getId());
		dto.setUsername(admin.getUsername());
		dto.setPassword(admin.getPassword());
		dto.setEmail(admin.getEmail());

		return dto;

	}

	/**
	 * Convierte un objeto AdminDTO en un objeto Admin.
	 * 
	 * @param admin
	 * @return entity
	 */
	public Admin toEntity(AdminDTO admin) {

		Admin entity = new Admin();

		entity.setId(admin.getId());
		entity.setUsername(admin.getUsername());
		entity.setPassword(admin.getPassword());
		entity.setEmail(admin.getEmail());

		return entity;
	}

	/**
	 * Obtiene una lista de administradores limitada.
	 * @param size
	 * @return adminList
	 */
	public List<AdminDTO> getAdmin(int size) {
		if (size > adminList.size()) {
			List<AdminDTO> shortenedList = new ArrayList<>();
			for (int i = 0; i < adminList.size() - 1; i++) {
				shortenedList.add(adminList.get(i));
			}
			return shortenedList;
		} else {
			return new ArrayList<>(adminList.subList(0, size));
		}
	}

	/**
	 * Realiza la autenticacion de un usuario.
	 * 
	 * @param username
	 * @param password
	 * @return true si es exitosa, false de lo contrario
	 */
	public boolean login(String username, String password) {

		posibleAdmin = findUserByUsername(username);

		if (posibleAdmin != null) {
			String adminPassword = posibleAdmin.getPassword();
			if (adminPassword.equals(password) && adminPassword != null) {
				return true;
			}
		}
		return false;

	}

	/*
	 * Crea un nuevo administrador en la base de datos
	 * y actualiza la lista de administradores.
	 */
	public void create(AdminDTO obj) {

		aDAO.create(toEntity(obj));
		adminList = readAll();

	}

	/**
	 * Lee todos los administradores en la base de datos. 
	 */
	public List<AdminDTO> readAll() {
		adminList.clear();
		ArrayList<Admin> entities = aDAO.readAll();
		for (Admin admin : entities) {

			adminList.add(toDTO(admin));
		}
		return adminList;
	}

	/**
	 * Obtine el numero total de administradores en la lista.
	 * @return adminList
	 */
	public int count() {

		return adminList.size();

	}

	/**
	 * Busca un administrador en la lista por el nombre de usuario(username)
	 * @param usernameSearch
	 * @return adminDTO
	 */
	public AdminDTO findUserByUsername(String usernameSearch) {

		for (AdminDTO adminDTO : adminList) {

			if (adminDTO.getUsername().equals(usernameSearch)) {
				return adminDTO;
			}

		}
		return null;

	}

	/**
	 * Verifica si un administrador con el nombre de usuario ya existe en la base de datos.
	 * @param username
	 * @return adminExist
	 */
	public boolean userExist(String username) {
		return aDAO.adminExist(username);
	}

	/**
	 * Obtiene la lista de administradores.
	 * @return adminList
	 */
	public List<AdminDTO> getAdminList() {
		return adminList;
	}

	/**
	 * Establece la lista de administradores.
	 * @param adminList
	 */
	public void setAdminList(List<AdminDTO> adminList) {
		this.adminList = adminList;
	}

	/**
	 * Obtiene el objeto AdminDAO.
	 * @return aDAO
	 */
	public AdminDAO getaDAO() {
		return aDAO;
	}

	/**
	 * Establece el objeto AdminDAO.
	 * @param aDAO
	 */
	public void setaDAO(AdminDAO aDAO) {
		this.aDAO = aDAO;
	}

	/**
	 * Elimina un administrador de la base de datos.
	 */
	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Actualiza la info de un administrador de la base de datos.
	 */
	@Override
	public boolean update(long id, AdminDTO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Busca un administrador por su id en la base de datos.
	 */
	@Override
	public AdminDTO findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtiene el posible administrador encontrado en la autenticacion.
	 * @return posibleAdmin
	 */
	public AdminDTO getPosibleAdmin() {
		return posibleAdmin;
	}

	/**
	 * Establece el posible administrador encontrado.
	 * @param posibleAdmin
	 */
	public void setPosibleAdmin(AdminDTO posibleAdmin) {
		this.posibleAdmin = posibleAdmin;
	}

}