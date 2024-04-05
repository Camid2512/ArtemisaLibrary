package co.edu.unbosque.model;

/**
 * Clase JavaTopicDTO almacena los atributos de los temas de Java de la biblioteca,
 * con sus respectivos constructoress, getters y setters.
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 28/03/2024
 */
public class JavaTopicDTO {

	/**
	 * Atributos de la clase JavaTopicDTO.
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
	 * Variable difficulty.
	 */
	private int difficulty;

	/**
	 * Constructor vacio de la clase JavaTopicDTO.
	 */
	public JavaTopicDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor propio de la clase JavaTopicDTO.
	 * 
	 * @param id            almacena el id del tema de Java.
	 * @param topicName     almacena el nombre del tema de Java.
	 * @param description   almacena la descripcion del tema de Java.
	 * @param code          almacena el codigo del respectivo tema de Java.
	 * @param difficulty    almacena el numero de dificultad del tema de Java.
	 */
	public JavaTopicDTO(long id, String topicName, String description, String code, int difficulty) {
		super();
		this.id = id;
		this.topicName = topicName;
		this.description = description;
		this.code = code;
		this.difficulty = difficulty;
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
	 * get del atributo description.
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
