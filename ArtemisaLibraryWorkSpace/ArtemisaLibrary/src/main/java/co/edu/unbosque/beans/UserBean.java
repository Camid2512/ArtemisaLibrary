package co.edu.unbosque.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
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
    
    public void createUser() {
        // Verificar si el correo cumple con el patrón esperado
        String correoUsuario = newUser.getEmail();
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
        userService.create(newUser);
        Email email = new Email(correoUsuario, "Bienvenido a Biblioteca Artemisa",
                "¡Gracias por registrarte en Biblioteca Artemisa!");
        emailSenderBean.setEmail(email);
        emailSenderBean.sendEmail();
        //tabla.saveUser();
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
        if (pass==true) {
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

	
    
    // Método para obtener el número de usuarios registrados
//    public int getNumeroUsuariosRegistrados() {
//        return userService.getNumeroUsuariosRegistrados();
//    }
//    
//    
//    public void getUsuariosPorCarrera() {
//        Map<String, Integer> usuariosPorCarrera = userService.getUsuariosPorCarrera();
//        
//        ChartData data = new ChartData();
//        PieChartDataSet dataSet = new PieChartDataSet();
//        List<Number> values = new ArrayList<>();
//        List<String> labels = new ArrayList<>();
//
//        usuariosPorCarrera.forEach((carrera, cantidad) -> {
//            labels.add(carrera);
//            values.add(cantidad);
//        });
//
//        dataSet.setData(values);
//        data.addChartDataSet(dataSet);
//        data.setLabels(labels);
//
//        usuariosPorCarreraChartModel.setData(data);
//    }
//    
//    public PieChartModel getUsuariosPorCarreraChartModel() {
//        getUsuariosPorCarrera(); // Actualizar datos del gráfico
//        return usuariosPorCarreraChartModel;
//    }
//    
//    public void createUsuariosPorSemestreChartModel() {
//        usuariosPorSemestreChartModel = new BarChartModel();
//        ChartData data = new ChartData();
//
//        Map<Integer, Integer> usuariosPorSemestre = userService.getUsuariosPorSemestre();
//        List<Number> values = new ArrayList<>();
//        List<String> labels = new ArrayList<>();
//
//        usuariosPorSemestre.forEach((semestre, cantidad) -> {
//        	labels.add(String.valueOf(semestre));
//            values.add(cantidad);
//        });
//
//        BarChartDataSet dataSet = new BarChartDataSet();
//        dataSet.setData(values);
//        data.addChartDataSet(dataSet);
//
//        data.setLabels(labels);
//        usuariosPorSemestreChartModel.setData(data);
//    }
    
    



}
