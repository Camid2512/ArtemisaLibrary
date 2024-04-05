package co.edu.unbosque.model;

/**
 * La clase PythonTopicDTO alamacena los atributos de los temas de Python
 * de la biblioteca, con sus respectivos constructores, getters y setters.
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 28/03/2024
 */
public class PythonTopicDTO {

	/**
	 * Atributos de la clase PythonTopicDTO.
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
	 * Constructor vacio de la clase PythonTopicDTO.
	 */
	public PythonTopicDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor propio de la clase PytonTopicDTO.
	 * 
	 * @param id            almacena el id del tema de Python.
	 * @param topicName     almacena el nombre del tema de Python.
	 * @param description   almacena la descripcion del tema de Python.
	 * @param code          almacena el respectivo codigo del tema de Python.
	 * @param difficulty    almacena el numero de dificultad del tema de Python.
	 */
	public PythonTopicDTO(long id, String topicName, String description, String code, int difficulty) {
		super();
		this.id = id;
		this.topicName = topicName;
		this.description = description;
		this.code = code;
		this.difficulty = difficulty;
	}

	/**
	 * get de id, permite obtener el id del tema de Python.
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * set de id, permite modificar el id del tema de Python.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get de topicName, permite obtener el nombre del tema de Python.
	 * @return topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * set de topicName, permite modificar y establecer el nombre del tema de Python.
	 * @param topicName
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * get de description, permite obtener la descripcion del tema de Python.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set de description, permite modificar la descripcion del tema de Python.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get de la variable code, permite obtener el respectivo codigo de programacion del tema de Python.
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * set de code, permite modificar el codigo del tema de Python.
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * get de difficulty, permite obtener el numero de dificultad del tema de Python.
	 * @return difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * set de difficulty, permite modificar el numero de dificultad del tema de Python.
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
