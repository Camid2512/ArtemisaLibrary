package co.edu.unbosque.model;

/**
 * Clase AdminDTO dministra y almacena los atrbutos de un administrador, ademas
 * de los constructores y respectivos getters y setters de la clase.
 * 
 * @author Cristhian Diaz
 * @version 1.0
 * @since 27/03/2024
 * 
 */
public class AdminDTO {

	/**
	 * Atributos de AdminDTO.
	 * 
	 */

	/**
	 * Variable id tipo long.
	 */
	private long id;

	/**
	 * Variable username.
	 */
	private String username;

	/**
	 * Variable password.
	 */
	private String password;

	/**
	 * Variable email.
	 */
	private String email;

	/**
	 * Constructor vacio de la clase AdminDTO.
	 */
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor propio de la clase AdminDTO.
	 * 
	 * @param id       almacena el id o numero del adimistrador.
	 * @param username almacena el nombre de usuario que tiene el administrador.
	 * @param password almacena la contraseña de cada administrador.
	 * @param email    almacena el correo del administrador.
	 */
	public AdminDTO(long id, String username, String password, String email) {
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
