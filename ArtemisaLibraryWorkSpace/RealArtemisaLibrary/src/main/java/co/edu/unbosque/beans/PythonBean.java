package co.edu.unbosque.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import co.edu.unbosque.model.PythonTopicDTO;
import co.edu.unbosque.service.PythonTopicService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("pythonBean")
@SessionScoped
public class PythonBean implements Serializable {

	@Inject
	private PythonTopicService pythonService;

	private static final long serialVersionUID = 4607905883171191425L;
	private List<PythonTopicDTO> topicsInTable;
	private PythonTopicDTO selectedTopic;
	private List<PythonTopicDTO> variousSelectedTopics;

	@PostConstruct
	public void init() {
		this.topicsInTable = new ArrayList<>();
		this.topicsInTable = pythonService.getTopics();
		this.variousSelectedTopics = new ArrayList<PythonTopicDTO>();

	}

	public void openNew() {
		this.selectedTopic = new PythonTopicDTO();
	}

	public void saveTopic() {
		if (this.selectedTopic.getId() == 0) {
			this.selectedTopic.setId(0);
			pythonService.create(selectedTopic);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMA AGREGADO"));
		} else {
			pythonService.update(selectedTopic.getId(), selectedTopic);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMA ACTUALIZADO"));
		}
		this.topicsInTable = pythonService.getTopics();
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

	}

	public void deleteTopic() {
		pythonService.delete(this.selectedTopic.getId());
		this.variousSelectedTopics.remove(this.selectedTopic);
		this.selectedTopic = null;
		this.topicsInTable = pythonService.getTopics();
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

		for (PythonTopicDTO pythonTopicDTO : variousSelectedTopics) {

			pythonService.delete(pythonTopicDTO.getId());

		}

		this.variousSelectedTopics = null;
		this.topicsInTable = pythonService.getTopics();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMAS ELIMINADOS"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");

	}

	public void exportToPDF() throws com.itextpdf.text.DocumentException {
		Document document = new Document();
		try {
			String filePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")
					+ "artemisa-python.pdf";
			try {
				PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.open();
			try {
				document.add(new Paragraph("Lista de Codigos Python\n\n"));
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (topicsInTable != null && !topicsInTable.isEmpty()) {
				for (PythonTopicDTO usuario : topicsInTable) {
					try {
						document.add(new Paragraph("ID: " + usuario.getId()));
						document.add(new Paragraph("Titulo: " + usuario.getTopicName()));
						document.add(new Paragraph("Descripcion: " + usuario.getDescription()));
						document.add(new Paragraph("Codigo: " + usuario.getCode()));
						document.add(new Paragraph("Dificultad: " + usuario.getDifficulty()));

						document.add(new Paragraph("\n"));
					} catch (com.itextpdf.text.DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				document.add(new Paragraph("No hay archivos para exportar."));
			}

			document.close();

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			File file = new File(filePath);
			byte[] content = Files.readAllBytes(file.toPath());
			externalContext.responseReset();
			externalContext.setResponseContentType("application/pdf");
			externalContext.setResponseContentLength(content.length);
			externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
			externalContext.getResponseOutputStream().write(content);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (com.itextpdf.text.DocumentException | IOException e) {
			e.printStackTrace();
			// Agregar manejo específico de excepciones aquí
		}
	}

	public List<PythonTopicDTO> getProducts() {
		return topicsInTable;
	}

	public void setProducts(List<PythonTopicDTO> products) {
		this.topicsInTable = products;
	}

	public PythonTopicService getPythonService() {
		return pythonService;
	}

	public void setPythonService(PythonTopicService pythonService) {
		this.pythonService = pythonService;
	}

	public List<PythonTopicDTO> getTopicsInTable() {
		return topicsInTable;
	}

	public void setTopicsInTable(List<PythonTopicDTO> topicsInTable) {
		this.topicsInTable = topicsInTable;
	}

	public PythonTopicDTO getSelectedTopic() {
		return selectedTopic;
	}

	public void setSelectedTopic(PythonTopicDTO selectedTopic) {
		this.selectedTopic = selectedTopic;
	}

	public List<PythonTopicDTO> getVariousSelectedTopics() {
		return variousSelectedTopics;
	}

	public void setVariousSelectedTopics(List<PythonTopicDTO> variousSelectedTopics) {
		this.variousSelectedTopics = variousSelectedTopics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
