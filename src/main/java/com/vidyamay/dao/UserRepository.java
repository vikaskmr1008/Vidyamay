/**
 * 
 */
package com.vidyamay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vidyamay.model.User;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@Repository
public interface UserRepository {
    
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<User> findAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findById(Long id);
	
	/**
	 * Save.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User save(User user);
}
