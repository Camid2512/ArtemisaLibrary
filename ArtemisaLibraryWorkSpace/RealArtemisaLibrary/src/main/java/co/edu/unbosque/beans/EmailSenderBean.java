package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.Properties;

import co.edu.unbosque.model.Email;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Esta clase permite enviar correor electronicos
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 01/04/2024
 */
@Named("emailSenderBean")
@SessionScoped
public class EmailSenderBean implements Serializable {

	/**
	 * Identificador unico de la version de la clase para la serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Inicializacion con un nuevo objeto email
	 */
	private Email email = new Email();

	/**
	 * Funcion que permite obtener el email proporcionado
	 * @return email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * Funcion que permite enviar el email proporcionado
	 */
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
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("softpylsa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getRecipient()));
			message.setSubject(email.getSubject());
			message.setText(email.getContent());
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene el valor del serialVersionUID.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Set del atributo email
	 * @param email
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

}
