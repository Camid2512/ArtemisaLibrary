package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import co.edu.unbosque.model.UserDTO;
import co.edu.unbosque.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase que permite gestionar las tablas del usuario
 * 
 * @author Erika Mesa
 * @version 1.0
 * @since 01/04/2024
 */
@Named("tableUserBean")
@SessionScoped
public class TableUserBean implements Serializable {

	/**
	 * Atributo que llama a la clase UserService
	 */
	@Inject
	private UserService userService;

	/**
	 * Identificador unico de la version de la clase para la serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que llama la lista de los usuarios en la tabla
	 */
	private List<UserDTO> userTable;

	/**
	 * Atributo que llama a la clase UserDTO
	 */
	private UserDTO userSelected;

	/**
	 * Atributo que llama la lista de los usuarios seleccionados en la tabla
	 */
	private List<UserDTO> userSelectedMany;

	/**
	 * Constructor que inicializa la lista de los usuarios al momento de cargar por
	 * primera vez la pagina
	 */
	@PostConstruct
	public void init() {
		this.userTable = new ArrayList<>();

		this.userTable = userService.getUser();
		this.userTable = new ArrayList<UserDTO>();
	}

	/**
	 * Funcion para iniciar la creacion de un nuevo DTO
	 */
	public void openNew() {
		this.userSelected = new UserDTO();
	}

	/**
	 * Funcion que permite guardar un usuario en la base de datos
	 */
	public void saveUser() {
		if (this.userSelected.getId() == 0) {
			this.userSelected.setId(0);
			userService.create(userSelected);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado"));
		} else {
			userService.update(userSelected.getId(), userSelected);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario actualizado"));
		}
		this.userTable = userService.getUser();
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}

	/**
	 * Funcion que permite eliminar un usuario en la base de datos
	 */
	public void deleteUser() {
		userService.delete(this.userSelected.getId());
		this.userSelectedMany.remove(this.userSelected);
		this.userSelected = null;
		this.userTable = userService.getUser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	/**
	 * Funcion que revisa si existe algun usuario seleccionado
	 * 
	 * @return "Eliminado"  
	 */
	public String getDeleteButtonMessage() {
		if (hasSelectedProducts()) {
			int size = this.userSelectedMany.size();
			return size > 1 ? size + " usuarios seleccionados" : "1 usuario seleccionado";
		}

		return "Eliminado";
	}

	/**
	 * Funcion que revisa si existen usuarios selecionados
	 * 
	 * @return boolean
	 */
	public boolean hasSelectedProducts() {
		return this.userSelectedMany != null && !this.userSelectedMany.isEmpty();
	}

	/**
	 * Funcion que permite eliminar usuarios selecionados
	 */
	public void deleteSelectedProducts() {
		for (UserDTO p : userSelectedMany) {
			userService.delete(p.getId());
		}
		this.userSelectedMany = null;
		this.userTable = userService.getUser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("usuarios eliminados"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	/**
	 * Get del atributo userService
	 * 
	 * @return userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Set del atributo userService
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Get del atributo userTable
	 * 
	 * @return userTable
	 */
	public List<UserDTO> getUserTable() {
		return userTable;
	}

	/**
	 * Set del atributo userTable
	 * 
	 * @param userTable
	 */
	public void setUserTable(List<UserDTO> userTable) {
		this.userTable = userTable;
	}

	/**
	 * Get del atributo userSelected
	 * 
	 * @return userSelected
	 */
	public UserDTO getUserSelected() {
		return userSelected;
	}

	/**
	 * Set del atributo userSelected
	 * 
	 * @param userSelected
	 */
	public void setUserSelected(UserDTO userSelected) {
		this.userSelected = userSelected;
	}

	/**
	 * Get del atributo userSelectedMany
	 * 
	 * @return userSelectedMany
	 */
	public List<UserDTO> getUserSelectedMany() {
		return userSelectedMany;
	}

	/**
	 * Set del atributo userSelectedMany
	 * 
	 * @param userSelectedMany
	 */
	public void setUserSelectedMany(List<UserDTO> userSelectedMany) {
		this.userSelectedMany = userSelectedMany;
	}

	/**
	 * Obtiene el valor del serialVersionUID.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
