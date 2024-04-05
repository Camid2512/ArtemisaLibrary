package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
/**
 * La clase PythonTopic representa los temas de Python de la biblioteca Artemisa,
 * contiene los atributos de la clase PythonTopicDTO e incluye anotaciones
 * para mapeo con la base de datos.
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 28/03/2024
 */
@Entity
@Table(name = "pythontopics")
public class PythonTopic {

	/**
	 * identificado unico para el tema de Python.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Nombre del tema de Python, no puede ser nulo.
	 */
	@Column(name = "topicname", nullable = false)
	private String topicName;

	/**
	 * Descripcion del tema de Python, no puede ser nula.
	 */
	@Lob
	@Column(name = "description", nullable = false)
	private String description;
	
	/**
	 * Codigo del tema de Python, no puede ser nulo.
	 */
	@Lob
	@Column(name = "code", nullable = false)
	private String code;

	/**
	 * Numero de dificltad del tema de Python, no puede ser nulo.
	 */
	@Column(name = "difficulty", nullable = false)
	private int difficulty;

	/**
	 * Constructor vacio de la clase PythonTopic.
	 */
	public PythonTopic() {
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
