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

/**
 * Clase que permite gestionar las operaciones del usuario
 * 
 * @author Valentina Lara
 * @version 1.0
 * @since 01/04/2024
 */
@Named("userBean")
@ApplicationScoped
public class UserBean {

	/**
	 * Atributo usernameNew tipo String
	 */
	private String usernameNew;

	/**
	 * Atributo passwordNew tipo String
	 */
	private String passwordNew;

	/**
	 * Atributo emailNew tipo String
	 */
	private String emailNew;

	/**
	 * Atributo usernameLogin tipo String
	 */
	private String usernameLogin;

	/**
	 * Atributo passwordLogin tipo String
	 */
	private String passwordLogin;

	/**
	 * Atributo que llama a la clase EmailSenderBean
	 */
	@Inject
	private EmailSenderBean emailSenderBean;

	/**
	 * Atributo que llama a la clase UserService
	 */
	@Inject
	private UserService userService;

	/**
	 * Creacion de un nuevo UserDTO
	 */
	private UserDTO newUser = new UserDTO();

	/**
	 * Creacion de un nuevo PieChartModel
	 */
	private PieChartModel userPorCarreraChartModel = new PieChartModel();

	/**
	 * Atributo que llama a la clase AdminService
	 */
	@Inject
	private AdminService adminService;

	/**
	 * Creacion de un nuevo AdminDTO
	 */
	private AdminDTO newAdmin = new AdminDTO();

	/**
	 * inicializacion de los atributos
	 */
	@PostConstruct
	public void init() {
		newAdmin = new AdminDTO();
		newAdmin.setEmail("");
		newUser = new UserDTO();
		newUser.setEmail("");

	}

	/**
	 * Funcion para crear usuario
	 */
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

	/**
	 * Funcion que valida que el correo este correctamente diligenciado
	 * 
	 * @param correo
	 * @return true or false
	 */
	private boolean validateEmail(String correo) {
		String patronCorreo = "^[a-zA-Z0-9_.+-]+@unbosque\\.edu\\.co$";
		Pattern pattern = Pattern.compile(patronCorreo);
		Matcher matcher = pattern.matcher(correo);
		return matcher.matches();
	}

	/**
	 * Funcion para iniciar sesion del usuario
	 */
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

	/**
	 * Get del atributo usernameNew
	 * 
	 * @return usernameNew
	 */
	public String getUsernameNew() {
		return usernameNew;
	}

	/**
	 * Set del atributo usernameNew
	 * 
	 * @param usernameNew
	 */
	public void setUsernameNew(String usernameNew) {
		this.usernameNew = usernameNew;
	}

	/**
	 * Get del atributo passwordNew
	 * 
	 * @return passwordNew
	 */
	public String getPasswordNew() {
		return passwordNew;
	}

	/**
	 * Set del atributo passwordNew
	 * 
	 * @param passwordNew
	 */
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	/**
	 * Get del atributo emailNew
	 * 
	 * @return emailNew
	 */
	public String getEmailNew() {
		return emailNew;
	}

	/**
	 * Set del atributo emailNew
	 * 
	 * @param emailNew
	 */
	public void setEmailNew(String emailNew) {
		this.emailNew = emailNew;
	}

	/**
	 * Get del atributo usernameLogin
	 * 
	 * @return usernameLogin
	 */
	public String getUsernameLogin() {
		return usernameLogin;
	}

	/**
	 * Set del atributo usernameLogin
	 * 
	 * @param usernameLogin
	 */
	public void setUsernameLogin(String usernameLogin) {
		this.usernameLogin = usernameLogin;
	}

	/**
	 * Get del atributo passwordLogin
	 * 
	 * @return passwordLogin
	 */
	public String getPasswordLogin() {
		return passwordLogin;
	}

	/**
	 * Set del atributo passwordLogin
	 * 
	 * @param passwordLogin
	 */
	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	/**
	 * Get del atributo emailSenderBean
	 * 
	 * @return emailSenderBean
	 */
	public EmailSenderBean getEmailSenderBean() {
		return emailSenderBean;
	}

	/**
	 * Set del atributo emailSenderBean
	 * 
	 * @param emailSenderBean
	 */
	public void setEmailSenderBean(EmailSenderBean emailSenderBean) {
		this.emailSenderBean = emailSenderBean;
	}

	/**
	 * Get del atributo userService
	 * 
	 * @return userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Set del atributo userService
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Get del atributo newUser
	 * 
	 * @return newUser
	 */
	public UserDTO getNewUser() {
		return newUser;
	}

	/**
	 * Set del atributo newUser
	 * 
	 * @param newUser
	 */
	public void setNewUser(UserDTO newUser) {
		this.newUser = newUser;
	}

	/**
	 * Get del atributo userPorCarreraChartModel
	 * 
	 * @return variousSelectedTopics
	 */
	public PieChartModel getUserPorCarreraChartModel() {
		return userPorCarreraChartModel;
	}

	/**
	 * Set del atributo userPorCarreraChartModel
	 * 
	 * @param userPorCarreraChartModel
	 */
	public void setUserPorCarreraChartModel(PieChartModel userPorCarreraChartModel) {
		this.userPorCarreraChartModel = userPorCarreraChartModel;
	}

	/**
	 * Get del atributo adminService
	 * 
	 * @return adminService
	 */
	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * Get del atributo newAdmin
	 * 
	 * @return newAdmin
	 */

	public AdminDTO getNewAdmin() {
		return newAdmin;
	}

	/**
	 * Set del atributo newAdmin
	 * 
	 * @param newAdmin
	 */
	public void setNewAdmin(AdminDTO newAdmin) {
		this.newAdmin = newAdmin;
	}

}
