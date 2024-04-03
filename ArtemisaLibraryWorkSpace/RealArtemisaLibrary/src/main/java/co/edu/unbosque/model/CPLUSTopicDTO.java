package co.edu.unbosque.model;

public class CPLUSTopicDTO {

	private long id;
	private String topicName;
	private String description;
	private String code;
	private int difficulty;

	public CPLUSTopicDTO() {
		// TODO Auto-generated constructor stub
	}

	public CPLUSTopicDTO(long id, String topicName, String description, String code, int difficulty) {
		super();
		this.id = id;
		this.topicName = topicName;
		this.description = description;
		this.code = code;
		this.difficulty = difficulty;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
