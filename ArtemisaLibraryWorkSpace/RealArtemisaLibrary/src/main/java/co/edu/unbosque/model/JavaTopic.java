package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * Clase JavaTopic representa los temas de Java en la biblioteca,
 * contiene los atributos de la clase JavaTopicDTO e incluye anotaciones
 * para el mapeo con la base de datos.
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 28/03/2024
 */
@Entity
@Table(name = "javatopics")
public class JavaTopic {

	/**
	 * Identificador unico para el tema de Java.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Nombre del tema de Java, no puede ser nulo.
	 */
	@Column(name = "topicname", nullable = false)
	private String topicName;

	/**
	 * Descripcion del respectivo tema de Java, no puede ser nulo.
	 */
	@Lob
	@Column(name = "description", nullable = false)
	private String description;

	/**
	 * Codigo del respectivo tema de Java, no puede ser nulo.
	 */
	@Lob
	@Column(name = "code", nullable = false)
	private String code;

	/**
	 * Numero de dificultad del tema de Java, no puede ser nulo.
	 */
	@Column(name = "difficulty", nullable = false)
	private int difficulty;

	/**
	 * Constructor vacio de la clase JavaTopic.
	 */
	public JavaTopic() {
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
	 * get de la variable difficulty.
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
