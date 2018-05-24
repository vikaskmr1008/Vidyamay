/**
 * 
 */
package com.vidyamay.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
public interface CRUDService<T>  {

	T save(T entity);

	T getById(Serializable id);

	List<T> getAll();

	void delete(Serializable id);
}
