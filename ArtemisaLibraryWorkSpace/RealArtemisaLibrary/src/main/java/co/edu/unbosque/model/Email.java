package co.edu.unbosque.model;

/**
 * Clase Email indica un correo electronico con destinatario, asunto y
 * contenido,se utiliza para crear y manipular mensajes de correo electronico
 * que se enviaran.
 * 
 * @author Cristhian Diaz
 * @version 1.0
 * @since 27/03/2024
 * 
 */
public class Email {

	/**
	 * Atributos de la clase Email
	 */

	/**
	 * Variable recipient.
	 */
	private String recipient;

	/**
	 * Variable subject.
	 */
	private String subject;

	/**
	 * Variable content.
	 */
	private String content;

	/**
	 * Constructor vacio de la clase Email Inicializa el asunto y el contenido
	 * predeterminados del correo electrónico de bienvenida.
	 */
	public Email() {
		subject = "¡Bienvenido a la biblioteca artemisa!";
		content = "USTED SE HA REGISTRADO CORRECTAMENTE PARA ACCEDER A LA BIBLIOTECA ARTEMISA";
	}

	/**
	 * Cosntructor propio de la clase Email
	 * 
	 * @param recipient almacena la direccion del destinatario que recibe el correo.
	 * @param subject   almacena el asusnto del correo electronico.
	 * @param content   almacena el contenido del correo el electronico.
	 */
	public Email(String recipient, String subject, String content) {
		super();
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * get del atributo recipient.
	 * 
	 * @return recipent
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * set de recipient.
	 * 
	 * @param recipient
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * get del atributo subject.
	 * 
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * set de subject.
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * get del atributo content.
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set de content.
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
