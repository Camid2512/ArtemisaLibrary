package co.edu.unbosque.beans;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.model.charts.pie.PieChartModel;

import co.edu.unbosque.model.Email;
import co.edu.unbosque.model.UserDTO;
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
	private UserDTO userLoged;
	private PieChartModel userPorCarreraChartModel = new PieChartModel();

	@PostConstruct
	public void init() {
		newUser = new UserDTO();
		newUser.setEmail("");

	}

	public void loginTest() {
		System.out.println("QUE RICO");
		String usernameUser = usernameLogin;
		String passwordUser = passwordLogin;

		int aux = 0;

		if (usernameUser.equals("test") || passwordUser.equals("test")) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bibliotecaartemisa.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			aux++;
		}
		if (aux > 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "USUARIO O CONTRASEÑA INCORRECTAS"));
		}
	}

	public void createUser() {

		System.out.println("YA ENTRO");
		// Verificar si el correo cumple con el patrón esperado
		String correoUsuario = emailNew;
		if (!validateEmail(correoUsuario)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El correo debe terminar en @unbosque.edu.co"));
			return;
		}
		if (userService.userExist(correoUsuario)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Ya existe un usuario registrado con este correo electrónico"));
			return;
		}
		newUser.setId(0);
		newUser.setUsername(usernameNew);
		newUser.setPassword(passwordNew);
		newUser.setEmail(emailNew);

		System.out.println(usernameNew);
		System.out.println(passwordNew);
		System.out.println(emailNew);
		System.out.println("----------");

		userService.create(newUser);
		Email email = new Email(correoUsuario, "Bienvenido a Biblioteca Artemisa",
				"¡Gracias por registrarte en Biblioteca Artemisa!");
		emailSenderBean.setEmail(email);
		emailSenderBean.sendEmail();
		// tabla.saveUser();
		newUser = new UserDTO();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("mainpage.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean validateEmail(String correo) {
		String patronCorreo = "^[a-zA-Z0-9_.+-]+@unbosque\\.edu\\.co$";
		Pattern pattern = Pattern.compile(patronCorreo);
		Matcher matcher = pattern.matcher(correo);
		return matcher.matches();
	}

	public String logIn() {
		boolean pass = userService.login(newUser.getEmail(), newUser.getPassword());
		if (pass == true) {
			// Inicio de sesión exitoso, redirigir a la página principal
			return "bibliotecartemisa.xhtml?faces-redirect=true";

		} else {
			// Inicio de sesión fallido, mostrar un mensaje de error
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Inicio de sesión fallido", "Correo electrónico o contraseña incorrectos"));
			newUser.setEmail("");
			newUser.setPassword("");
			return null;
		}
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

	public UserDTO getUserLoged() {
		return userLoged;
	}

	public void setUserLoged(UserDTO userLoged) {
		this.userLoged = userLoged;
	}

	public PieChartModel getUserPorCarreraChartModel() {
		return userPorCarreraChartModel;
	}

	public void setUserPorCarreraChartModel(PieChartModel userPorCarreraChartModel) {
		this.userPorCarreraChartModel = userPorCarreraChartModel;
	}

}
