/**
 * 
 */
package com.vidyamay.dataaccess;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
public interface DataRepository {
    
	/**
	 * @param <T>
	 * @param object
	 * @return
	 */
	public <T> Mono<T> save(T resource);

	/**
	 * @param <T>
	 * @param t
	 * @return
	 */
	public <T> Mono<T> update(final T resource);
	
	/**
	 * @param clazz
	 */
	public <T> void delete(final T resource);

	/**
	 * @param clazz
	 * @param id
	 */
	public <T> void delete(final Class<T> clazz, Long id);

	/**
	 * @param <T>
	 * @param id
	 * @return
	 */
	public <T> Mono<T> findById(final Class<T> clazz, Long id);
	
	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <T>  T findOne(Class<T> clazz, Long id);

	/**
	 * @param clazz
	 * @return
	 */
	public <T> Flux<T> findAll(final Class<T> clazz);

}
