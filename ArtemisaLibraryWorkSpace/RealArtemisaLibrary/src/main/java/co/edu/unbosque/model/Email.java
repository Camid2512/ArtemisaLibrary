package co.edu.unbosque.model;

public class Email {

	private String recipient;
	private String subject;
	private String content;

	public Email() {
		// TODO Auto-generated constructor stub

		subject = "¡Bienvenido a la biblioteca artemisa!";
		content = "USTED SE HA REGISTRADO CORRECTAMENTE PARA ACCEDER A LA BIBLIOTECA ARTEMISA";
	}

	public Email(String recipient, String subject, String content) {
		super();
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
