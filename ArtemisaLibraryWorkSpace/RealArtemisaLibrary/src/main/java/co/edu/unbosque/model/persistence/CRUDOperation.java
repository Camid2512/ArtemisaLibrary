package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

/**
 * Esta clase genera los metodos que se implemntaran en clases sucundarias
 * 
 * @param <E>
 * @author Cristhian Diaz
 * @author 28/03/2024
 */
public interface CRUDOperation<E> {
	/**
	 * Funcion para crear un registro en la base de datos
	 * 
	 * @param obj
	 */
	public void create(E obj);

	/**
	 * Funcion para eliminar un registro en la base de datos, con su id
	 * 
	 * @param id
	 * @return true or false
	 */
	public boolean delete(long id);

	/**
	 * Funcion para actualizar un registro en la base de datos, con su id
	 * 
	 * @param id
	 * @param obj
	 * @return true or false
	 */
	public boolean update(long id, E obj);

	/**
	 * Funcion que toma todos los registros de la case de datos y los almacena en un
	 * ArrayList
	 * 
	 * @return ArrayList
	 */
	public ArrayList<E> readAll();

	/**
	 * Funcion para buscar un registro en especifico de la base detos, por su id
	 * 
	 * @param id
	 * @return ArrayList
	 */
	public E findOne(long id);

	/**
	 * Funcion que permite contar el numero total de registros en la base de datos
	 * 
	 * @return int
	 */
	public int count();

}
