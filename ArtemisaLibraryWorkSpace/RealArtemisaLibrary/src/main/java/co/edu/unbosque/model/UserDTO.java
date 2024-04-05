package co.edu.unbosque.model;

/**
 * Clase UserDTO que almacena los atributos de un usuario, ademas con sus
 * respectivos constructores, getters y setters.
 * 
 * @author Santiago Rueda
 * @version 1.0
 * @since 28/03/2024
 * 
 */
public class UserDTO {

	/**
	 * Atributos de la clase UserDTO.
	 */

	/**
	 * Atributo id de tipo long.
	 */
	private long id;

	/**
	 * Atributo username.
	 */
	private String username;

	/**
	 * Atributo password.
	 */
	private String password;

	/**
	 * Atributo email.
	 */
	private String email;

	/**
	 * Constructor vacio de la clase UserDTO.
	 */
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor propio de la clase UserDTO.
	 * 
	 * @param id       almacena el id del usuario.
	 * @param username almacena el nombre de usuario del usuario.
	 * @param password almacena la contraseña del usuario.
	 * @param email    almacena el correo del usuario.
	 */
	public UserDTO(long id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
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
	 * get del atributo password.
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
	 * get del atributo email.
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
