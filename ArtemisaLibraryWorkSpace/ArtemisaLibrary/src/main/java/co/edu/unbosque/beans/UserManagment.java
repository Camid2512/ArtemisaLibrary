package co.edu.unbosque.beans;

import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.Email;
import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.service.AdminService;
import co.edu.unbosque.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named("userManagmentBean")
@SessionScoped
public class UserManagment implements Serializable {

	@Inject
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	private UserService userService;
	private UserDTO newUser;
	private AdminDTO newAdmin;
	private boolean userMode = false;

	private EmailSenderBean emailSenderBean;

	@PostConstruct
	public void init() {
		newUser = new UserDTO();
		newAdmin = new AdminDTO();
	}

	public void createUser() {

		String emailAdmin = newAdmin.getEmail();
		String emailUser = newUser.getEmail();

		String usernameAdmin = newAdmin.getUsername();
		String usernameUser = newUser.getUsername();
		if (!verifyEmail(emailUser) || !verifyEmail(emailAdmin)) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Recuerde el correo debe terminar con @unbosque.edu.co"));
			return;

		}
		if (userService.userExist(usernameUser) || adminService.userExist(usernameAdmin)
				|| userService.userExist(usernameAdmin) || adminService.userExist(usernameUser)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"ESTE NOMBRE DE USUARIO YA ESTA EN USO, INTENTE NUEVAMENTE"));
			return;
		}

		if (emailAdmin.equals("dferrodriguezc@unbosque.edu.co")) {
			userMode = false;
			adminService.create(newAdmin);
			Email email = new Email(emailAdmin, "¡Bienvenido a la biblioteca artemisa!",
					"USTED SE HA REGISTRADO CORRECTAMENTE PARA ACCEDER A LA BIBLIOTECA ARTEMISA COMO ADMINISTRADOR");

			emailSenderBean.setEmail(email);
			emailSenderBean.sendEmail();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecaartemisaAdmin.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			userMode = true;
			userService.create(newUser);
			Email email = new Email(emailUser, "¡Bienvenido a la biblioteca artemisa!",
					"USTED SE HA REGISTRADO CORRECTAMENTE PARA ACCEDER A LA BIBLIOTECA ARTEMISA COMO UN NUEVO USUARIO");

			emailSenderBean.setEmail(email);
			emailSenderBean.sendEmail();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecaartemisa.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public boolean verifyEmail(String email) {

		return email.endsWith("@unbosque.edu.co");

	}

	public EmailSenderBean getEmailSenderBean() {
		return emailSenderBean;
	}

	public void setEmailSenderBean(EmailSenderBean emailSenderBean) {
		this.emailSenderBean = emailSenderBean;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserDTO getNewUser() {
		return newUser;
	}

	public void setNewUser(UserDTO newUser) {
		this.newUser = newUser;
	}

	public AdminDTO getNewAdmin() {
		return newAdmin;
	}

	public void setNewAdmin(AdminDTO newAdmin) {
		this.newAdmin = newAdmin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isUserMode() {
		return userMode;
	}

	public void setUserMode(boolean userMode) {
		this.userMode = userMode;
	}

}
