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

/**
 * Clase que permite la integracion entre el back y el front
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 01/04/2024
 */
@Named("pythonBean")
@SessionScoped
public class PythonBean implements Serializable {

	/**
	 * Atributo que llama a la clase pythonService
	 */
	@Inject
	private PythonTopicService pythonService;

	/**
	 * Identificador unico de la version de la clase para la serialización.
	 */
	private static final long serialVersionUID = 4607905883171191425L;

	/**
	 * Atributo que llama la lista de los temas en la tabla
	 */
	private List<PythonTopicDTO> topicsInTable;

	/**
	 * Atributo que llamma a la clase PythonTopicDTO
	 */
	private PythonTopicDTO selectedTopic;

	/**
	 * Atributo que llama la lista de los temas seleccionados en la tabla
	 */
	private List<PythonTopicDTO> variousSelectedTopics;

	/**
	 * Constructor que inicializa la lista de temas al momento de cargar por primera
	 * vez la pagina
	 */
	@PostConstruct
	public void init() {
		this.topicsInTable = new ArrayList<>();
		this.topicsInTable = pythonService.getTopics();
		this.variousSelectedTopics = new ArrayList<PythonTopicDTO>();

	}

	/**
	 * Funcion para iniciar la creacion de un nuevo DTO
	 */
	public void openNew() {
		this.selectedTopic = new PythonTopicDTO();
	}

	/**
	 * Funcion que permite guardar un tema en la base de datos
	 */
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

	/**
	 * Funcion que permite eliminar un tema en la base de datos
	 */
	public void deleteTopic() {
		pythonService.delete(this.selectedTopic.getId());
		this.variousSelectedTopics.remove(this.selectedTopic);
		this.selectedTopic = null;
		this.topicsInTable = pythonService.getTopics();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("TEMA ELIMINADO"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");

	}

	/**
	 * Funcion que revisa cuantos temas seleccionados seran eliminados
	 * 
	 * @return "Eliminado"
	 */
	public String getDeleteButtonMessage() {
		if (hasSelectedTopics()) {
			int size = this.variousSelectedTopics.size();
			return size > 1 ? size + " temas seleccionados" : "1 tema seleccionado";
		}
		return "Eliminado";
	}

	/**
	 * Funcion que revisa si existen temas selecionados
	 * 
	 * @return boolean
	 */
	public boolean hasSelectedTopics() {

		return this.variousSelectedTopics != null && !this.variousSelectedTopics.isEmpty();

	}

	/**
	 * Funcion que permite eliminar temas selecionados
	 */
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

	/**
	 * Funcion que exporta la tabla de todos los datos registrados a un pdf
	 * 
	 * @throws com.itextpdf.text.DocumentException
	 */
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

	/**
	 * Funcion que permite obtener todo el ArrayList del DTO para luego colocar la
	 * informacion en la tabla
	 * 
	 * @return topicsInTable
	 */
	public List<PythonTopicDTO> getProducts() {
		return topicsInTable;
	}

	/**
	 * Funcion que permite modificar todo el ArrayList del DTO para luego colocar la
	 * informacion en la tabla
	 * 
	 * @param products
	 */
	public void setProducts(List<PythonTopicDTO> products) {
		this.topicsInTable = products;
	}

	/**
	 * Get del atributo pythonService
	 * 
	 * @return pythonService
	 */
	public PythonTopicService getPythonService() {
		return pythonService;
	}

	/**
	 * Set del atributo pythonService
	 * 
	 * @param pythonService
	 */
	public void setPythonService(PythonTopicService pythonService) {
		this.pythonService = pythonService;
	}

	/**
	 * Get del atributo topicsInTable
	 * 
	 * @return topicsInTable
	 */
	public List<PythonTopicDTO> getTopicsInTable() {
		return topicsInTable;
	}

	/**
	 * Set del atributo topicsInTable
	 * 
	 * @param topicsInTable
	 */
	public void setTopicsInTable(List<PythonTopicDTO> topicsInTable) {
		this.topicsInTable = topicsInTable;
	}

	/**
	 * Get del atibuto selectedTopic
	 * 
	 * @return selectedTopic
	 */
	public PythonTopicDTO getSelectedTopic() {
		return selectedTopic;
	}

	/**
	 * Set del atributo selectedTopic
	 * 
	 * @param selectedTopic
	 */
	public void setSelectedTopic(PythonTopicDTO selectedTopic) {
		this.selectedTopic = selectedTopic;
	}

	/**
	 * Get del atributo variousSelectedTopic
	 * 
	 * @return variousSelectedTopics
	 */
	public List<PythonTopicDTO> getVariousSelectedTopics() {
		return variousSelectedTopics;
	}

	/**
	 * Set del atributo variousSelectedTopic
	 * 
	 * @param variousSelectedTopics
	 */
	public void setVariousSelectedTopics(List<PythonTopicDTO> variousSelectedTopics) {
		this.variousSelectedTopics = variousSelectedTopics;
	}

	/**
	 * Obtiene el valor del serialVersionUID.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
