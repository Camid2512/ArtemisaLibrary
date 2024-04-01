package co.edu.unbosque.beans;

import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.service.AdminService;
import co.edu.unbosque.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("userManagment")
@SessionScoped
public class UserManagment {

	private AdminService adminService;
	private UserService userService;
	private UserDTO newUser;
	private AdminDTO newAdmin;

	@PostConstruct
	public void init() {
		newUser = new UserDTO();
		newAdmin = new AdminDTO();
	}

	public void createUser() {

		String emailAdmin = newAdmin.getEmail();
		String emailUser = newUser.getEmail();
		if (!verifyEmail(emailUser) || !verifyEmail(emailAdmin)) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INVALIDO",
					"Recuerde el correo debe terminar con @unbosque.edu.co"));
			return;

		}

		if (emailAdmin.equals("dferrodriguezc@unbosque.edu.co")) {

			adminService.create(newAdmin);

		} else {
			userService.create(newUser);
		}

	}

	public boolean verifyEmail(String email) {

		return email.endsWith("@unbosque.edu.co");

	}

}
