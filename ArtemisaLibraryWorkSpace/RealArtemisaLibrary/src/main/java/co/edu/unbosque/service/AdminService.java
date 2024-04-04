package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.persistence.AdminDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AdminService implements ServiceOperation<AdminDTO> {

	private List<AdminDTO> adminList;
	private AdminDAO aDAO = new AdminDAO();
	private AdminDTO posibleAdmin = new AdminDTO();

	@PostConstruct
	public void init() {

		aDAO = new AdminDAO();
		adminList = new ArrayList<>();
		adminList = readAll();

	}

	public AdminDTO toDTO(Admin admin) {

		AdminDTO dto = new AdminDTO();
		dto.setId(admin.getId());
		dto.setUsername(admin.getUsername());
		dto.setPassword(admin.getPassword());
		dto.setEmail(admin.getEmail());

		return dto;

	}

	public Admin toEntity(AdminDTO admin) {

		Admin entity = new Admin();

		entity.setId(admin.getId());
		entity.setUsername(admin.getUsername());
		entity.setPassword(admin.getPassword());
		entity.setEmail(admin.getEmail());

		return entity;
	}

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

	public void create(AdminDTO obj) {

		aDAO.create(toEntity(obj));
		adminList = readAll();

	}

	public List<AdminDTO> readAll() {
		adminList.clear();
		ArrayList<Admin> entities = aDAO.readAll();
		for (Admin admin : entities) {

			adminList.add(toDTO(admin));
		}
		return adminList;
	}

	public int count() {

		return adminList.size();

	}

	public AdminDTO findUserByUsername(String usernameSearch) {

		for (AdminDTO adminDTO : adminList) {

			if (adminDTO.getUsername().equals(usernameSearch)) {
				return adminDTO;
			}

		}
		return null;

	}

	public boolean userExist(String username) {
		return aDAO.adminExist(username);
	}

	public List<AdminDTO> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<AdminDTO> adminList) {
		this.adminList = adminList;
	}

	public AdminDAO getaDAO() {
		return aDAO;
	}

	public void setaDAO(AdminDAO aDAO) {
		this.aDAO = aDAO;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(long id, AdminDTO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AdminDTO findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public AdminDTO getPosibleAdmin() {
		return posibleAdmin;
	}

	public void setPosibleAdmin(AdminDTO posibleAdmin) {
		this.posibleAdmin = posibleAdmin;
	}

}
