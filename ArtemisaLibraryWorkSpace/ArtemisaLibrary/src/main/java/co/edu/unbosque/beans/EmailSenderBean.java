package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.Properties;

import co.edu.unbosque.model.Email;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

@Named("emailSenderBean")
@SessionScoped
public class EmailSenderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Email email = new Email();

	public Email getEmail() {
		return email;
	}

	public void sendEmail() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("softpylsa@gmail.com", "fais wmhr cuxj cbkr");
			}

		});
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
