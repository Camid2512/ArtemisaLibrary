package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Esta clase Admin representa un administrador en la biblioteca, contiene los
 * atributos basicos de AdminDTO y tambien incluye anotaciones de JPA para mapeo
 * con la base de datos.
 * 
 * @author Cristhian Diaz
 * @version 1.0
 * @since 27/03/2024
 */
@Entity
@Table(name = "libraryadmin")
public class Admin {

	/**
	 * Identificador unico del administrador.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Nombre de usuario del administrador, debe ser unico y no nulo.
	 */
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	/**
	 * Contraseña del administrador, no puede ser nula
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * Correo electronico del administrador, no puede ser nulo
	 */
	@Column(name = "email", nullable = false)
	private String email;

	/**
	 * Constructor vacio de la clase Admin
	 */
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * get del atributo id.
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * set de id.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get del atributo username.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set de username.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get del atributo passsword.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set de password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get del atributo email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set de email.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
