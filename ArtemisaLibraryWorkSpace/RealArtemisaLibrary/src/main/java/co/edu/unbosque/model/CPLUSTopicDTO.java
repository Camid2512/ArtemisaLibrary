package co.edu.unbosque.model;

/**
 * Esta clase CPLUSTopicDTO administra y almacena los atributos
 * de los temas de la biblioteca, con sus respectuvos constructores, 
 * getters y setters.
 * 
 * @author Cristhian Diaz
 * @version 1.0
 * @since 27/03/2024
 */
public class CPLUSTopicDTO {

	/**
	 * Atributos de la clase CPLUSTopicDTO.
	 */
	
	/**
	 * Variable id de tipo long.
	 */
	private long id;
	
	/**
	 * Variable topicName.
	 */
	private String topicName;
	
	/**
	 * Variable description.
	 */
	private String description;
	
	/**
	 * Variable code.
	 */
	private String code;
	
	/**
	 * Variable difficulty de tipo int
	 */
	private int difficulty;

	/**
	 * Constructor vacio de la clase CPLUSTopicDTO
	 */
	public CPLUSTopicDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor propio de la clase CPLUSTopicDTO.
	 * @param id            almacena el id o numero del tema.
	 * @param topicName     almacena el nombre del tema.
	 * @param description   almacena una descripcion del tema.
	 * @param code          almacena el codigo en el respectivo lenguaje del tema.
	 * @param difficulty    almacena el numero de difcultad del tema.
	 */
	public CPLUSTopicDTO(long id, String topicName, String description, String code, int difficulty) {
		super();
		this.id = id;
		this.topicName = topicName;
		this.description = description;
		this.code = code;
		this.difficulty = difficulty;
	}

	/**
	 * get de la variable id.
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * set de la variable id.
	 * @param id 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get del atributo topicName.
	 * @return topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * set de topicName.
	 * @param topicName 
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * get de la variable description.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set de description.
	 * @param description 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get del atributo code.
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * set de code.
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * get del atributo difficulty.
	 * @return difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * set de difficulty.
	 * @param difficulty 
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
