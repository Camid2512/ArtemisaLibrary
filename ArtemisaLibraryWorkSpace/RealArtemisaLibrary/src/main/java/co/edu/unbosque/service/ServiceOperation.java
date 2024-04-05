package co.edu.unbosque.service;

import java.util.List;

/**
 * La interfaz ServiceOperation tiene operaciones que 
 * incluyen la creación, lectura, actualización y 
 * eliminación de objetos de cualquier tipo.
 * @param <E>
 * 
 * @author Valentina Lara
 * @version 1.0
 * @since 31/03/2024
 */
public interface ServiceOperation<E> {

	/**
	 * Crea un nuevo objeto.
	 * @param obj
	 */
	public void create(E obj);

	/**
	 * Elimina un objeto.
	 * @param id
	 * @return true si es exitosa, false lo contrario.
	 */
	public boolean delete(long id);

	/**
	 * Actualiza un objeto.
	 * @param id
	 * @param obj
	 * @return true si es exitosa, false lo contrario.
	 */
	public boolean update(long id, E obj);

	/**
	 * Obtiene todos los objetos.
	 * @return list
	 */
	public List<E> readAll();

	/**
	 * Encuentra un objeto dado su identificador.
	 * @param id
	 * @return el objeto encontrado, o null si no se encuentra.
	 */
	public E findOne(long id);

}
