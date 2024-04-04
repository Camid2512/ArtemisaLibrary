package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import co.edu.unbosque.model.JavaTopicDTO;
import co.edu.unbosque.service.JavaTopicService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("javaBean")
@SessionScoped
public class JavaBean implements Serializable {

	@Inject
	private JavaTopicService javaService;

	private static final long serialVersionUID = 4607905883171191425L;
	private List<JavaTopicDTO> topicsInTable;
	private JavaTopicDTO selectedTopic;
	private List<JavaTopicDTO> variousSelectedTopics;

	@PostConstruct
	public void init() {
		this.topicsInTable = new ArrayList<>();
		this.topicsInTable = javaService.getTopics();
		this.variousSelectedTopics = new ArrayList<JavaTopicDTO>();

	}

	public void openNew() {
		this.selectedTopic = new JavaTopicDTO();
	}

	public void saveTopic() {
		if (this.selectedTopic.getId() == 0) {
			this.selectedTopic.setId(0);
			javaService.create(selectedTopic);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMA AGREGADO"));
		} else {
			javaService.update(selectedTopic.getId(), selectedTopic);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMA ACTUALIZADO"));
		}
		this.topicsInTable = javaService.getTopics();
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

	}

	public void deleteTopic() {
		javaService.delete(this.selectedTopic.getId());
		this.variousSelectedTopics.remove(this.selectedTopic);
		this.selectedTopic = null;
		this.topicsInTable = javaService.getTopics();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMA ELIMINADO"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedTopics()) {
			int size = this.variousSelectedTopics.size();
			return size > 1 ? size + " temas seleccionados" : "1 tema seleccionado";
		}
		return "Eliminado";
	}

	public boolean hasSelectedTopics() {

		return this.variousSelectedTopics != null && !this.variousSelectedTopics.isEmpty();

	}

	public void deleteSelectedTopics() {

		for (JavaTopicDTO javaTopicDTO : variousSelectedTopics) {

			javaService.delete(javaTopicDTO.getId());

		}

		this.variousSelectedTopics = null;
		this.topicsInTable = javaService.getTopics();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMAS ELIMINADOS"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");

	}

	public List<JavaTopicDTO> getProducts() {
		return topicsInTable;
	}

	public void setProducts(List<JavaTopicDTO> products) {
		this.topicsInTable = products;
	}

	public JavaTopicService getJavaService() {
		return javaService;
	}

	public void setJavaService(JavaTopicService javaService) {
		this.javaService = javaService;
	}

	public List<JavaTopicDTO> getTopicsInTable() {
		return topicsInTable;
	}

	public void setTopicsInTable(List<JavaTopicDTO> topicsInTable) {
		this.topicsInTable = topicsInTable;
	}

	public JavaTopicDTO getSelectedTopic() {
		return selectedTopic;
	}

	public void setSelectedTopic(JavaTopicDTO selectedTopic) {
		this.selectedTopic = selectedTopic;
	}

	public List<JavaTopicDTO> getVariousSelectedTopics() {
		return variousSelectedTopics;
	}

	public void setVariousSelectedTopics(List<JavaTopicDTO> variousSelectedTopics) {
		this.variousSelectedTopics = variousSelectedTopics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
