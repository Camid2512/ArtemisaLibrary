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

@Named("tableUserBean")
@SessionScoped
public class TableUserBean implements Serializable {

	@Inject
	private UserService userService;

	private static final long serialVersionUID = 1L;

	private List<UserDTO> userTable;

	private UserDTO userSelected;

	private List<UserDTO> userSelectedMany;

	@PostConstruct
	public void init() {
		this.userTable = new ArrayList<>();

		this.userTable = userService.getUser();
		this.userTable = new ArrayList<UserDTO>();
	}

	public void openNew() {
		this.userSelected = new UserDTO();
	}

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

	public void deleteUser() {
		userService.delete(this.userSelected.getId());
		this.userSelectedMany.remove(this.userSelected);
		this.userSelected = null;
		this.userTable = userService.getUser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedProducts()) {
			int size = this.userSelectedMany.size();
			return size > 1 ? size + " usuarios seleccionados" : "1 usuario seleccionado";
		}

		return "Eliminado";
	}

	public boolean hasSelectedProducts() {
		return this.userSelectedMany != null && !this.userSelectedMany.isEmpty();
	}

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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<UserDTO> getUserTable() {
		return userTable;
	}

	public void setUserTable(List<UserDTO> userTable) {
		this.userTable = userTable;
	}

	public UserDTO getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(UserDTO userSelected) {
		this.userSelected = userSelected;
	}

	public List<UserDTO> getUserSelectedMany() {
		return userSelectedMany;
	}

	public void setUserSelectedMany(List<UserDTO> userSelectedMany) {
		this.userSelectedMany = userSelectedMany;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
