package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Esta clase User representa un usuario en la biblioteca Artemisa,
 * contiene los atributos basicos de UserDTO e incluye anotaciones
 * para mapeo con la base de datos.
 * 
 * @author Santiago Rueda
 * @version 1.0
 * @since 28/03/2024
 */
@Entity
@Table(name = "libraryuser")
public class User {

	/**
	 * identificador unico del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Nombre de usuario del usuario, debe ser unico y no nulo.
	 */
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	/**
	 * Contraseña del usuario, no puede ser nula.
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * Correo electronico del usuario, no puede ser nulo.
	 */
	@Column(name = "email", nullable = false)
	private String email;

	/**
	 * Constructor vacio de la clase User
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * get del atributo id.
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * set de id.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get del atributo username.
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set de username.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get del atributo password.
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set de password.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get del atributo email.
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set de email.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email=email;
	}

}
