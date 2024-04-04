package co.edu.unbosque.beans;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.model.charts.pie.PieChartModel;

import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.Email;
import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.service.AdminService;
import co.edu.unbosque.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("userBean")
@ApplicationScoped
public class UserBean {
	private String usernameNew;
	private String passwordNew;
	private String emailNew;

	private String usernameLogin;
	private String passwordLogin;

	@Inject
	private EmailSenderBean emailSenderBean;

	@Inject
	private UserService userService;
	private UserDTO newUser = new UserDTO();
	private PieChartModel userPorCarreraChartModel = new PieChartModel();
	@Inject
	private AdminService adminService;
	private AdminDTO newAdmin = new AdminDTO();

	@PostConstruct
	public void init() {
		newAdmin = new AdminDTO();
		newAdmin.setEmail("");
		newUser = new UserDTO();
		newUser.setEmail("");

	}

	public void createUser() {

		String userSaludate = usernameNew;

		if (!validateEmail(emailNew)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El correo debe terminar en @unbosque.edu.co"));
			return;
		}
		if (userService.userExist(usernameNew) || adminService.userExist(usernameNew)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Ya existe un usuario registrado con este nombre de usuario"));
			return;
		}
		if (emailNew.equals("dferodriguezc@unbosque.edu.co") || emailNew.equals("ccdiazr@unbosque.edu.co")
				|| emailNew.equals("enmesa@unbosque.edu.co") || emailNew.equals("lvlarar@unbosque.edu.co")
				|| emailNew.equals("gwakil@unbosque.edu.co") || emailNew.equals("asrueda@unbosque.edu.co")) {
			newAdmin.setId(0);
			newAdmin.setUsername(usernameNew);
			newAdmin.setPassword(passwordNew);
			newAdmin.setEmail(emailNew);

			adminService.create(newAdmin);

			Email email = new Email(emailNew, "Bienvenido a Biblioteca Artemisa", "Hola! " + userSaludate
					+ " ¡Gracias por registrarte en Biblioteca Artemisa! Ahora eres un NUEVO ADMINISTRADOR");
			emailSenderBean.setEmail(email);
			emailSenderBean.sendEmail();
			// tabla.saveUser();
			newUser = new UserDTO();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecartemisaAdmin.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			newUser.setId(0);
			newUser.setUsername(usernameNew);
			newUser.setPassword(passwordNew);
			newUser.setEmail(emailNew);

			System.out.println(usernameNew);
			System.out.println(passwordNew);
			System.out.println(emailNew);
			System.out.println("----------");

			userService.create(newUser);
			Email email = new Email(emailNew, "Bienvenido a Biblioteca Artemisa",
					"Hola! " + userSaludate + " Gracias por registrarte en Biblioteca Artemisa!");
			emailSenderBean.setEmail(email);
			emailSenderBean.sendEmail();
			// tabla.saveUser();
			newUser = new UserDTO();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecartemisa.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		usernameLogin = "";
		passwordLogin = "";
		usernameNew = "";
		passwordNew = "";
		emailNew = "";

	}

	private boolean validateEmail(String correo) {
		String patronCorreo = "^[a-zA-Z0-9_.+-]+@unbosque\\.edu\\.co$";
		Pattern pattern = Pattern.compile(patronCorreo);
		Matcher matcher = pattern.matcher(correo);
		return matcher.matches();
	}

	public void logIn() {
		if (usernameLogin.equals("admin") && passwordLogin.equals("1234")
				|| adminService.login(usernameLogin, passwordLogin)) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecartemisaAdmin.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (userService.login(usernameLogin, passwordLogin)) {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecartemisa.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"ERROR en el INICIO DE SESION", "Usuario o Contraseña INCORRECTOS"));
			}
		}
		usernameLogin = "";
		passwordLogin = "";
		usernameNew = "";
		passwordNew = "";
		emailNew = "";
	}

	public String getUsernameNew() {
		return usernameNew;
	}

	public void setUsernameNew(String usernameNew) {
		this.usernameNew = usernameNew;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getEmailNew() {
		return emailNew;
	}

	public void setEmailNew(String emailNew) {
		this.emailNew = emailNew;
	}

	public String getUsernameLogin() {
		return usernameLogin;
	}

	public void setUsernameLogin(String usernameLogin) {
		this.usernameLogin = usernameLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	public EmailSenderBean getEmailSenderBean() {
		return emailSenderBean;
	}

	public void setEmailSenderBean(EmailSenderBean emailSenderBean) {
		this.emailSenderBean = emailSenderBean;
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

	public PieChartModel getUserPorCarreraChartModel() {
		return userPorCarreraChartModel;
	}

	public void setUserPorCarreraChartModel(PieChartModel userPorCarreraChartModel) {
		this.userPorCarreraChartModel = userPorCarreraChartModel;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public AdminDTO getNewAdmin() {
		return newAdmin;
	}

	public void setNewAdmin(AdminDTO newAdmin) {
		this.newAdmin = newAdmin;
	}

}
