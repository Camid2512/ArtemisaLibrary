package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.persistence.AdminDAO;
import jakarta.annotation.PostConstruct;

public class AdminService {

	private List<AdminDTO> adminList;
	private AdminDAO aDAO = new AdminDAO();

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

}
