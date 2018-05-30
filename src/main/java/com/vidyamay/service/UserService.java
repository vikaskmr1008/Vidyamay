package com.vidyamay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidyamay.dataaccess.DataRepository;
import com.vidyamay.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@Service
public class UserService {
	
	/**
	 * 
	 */
	@Autowired DataRepository repository;

	
	/**
	 * @return
	 */
	public Flux<User> findAll() {
		return repository.findAll(User.class);
	}

	
	/**
	 * @param id
	 * @return
	 */
	public Mono<User> findById(Long id) {
		return repository.findById(User.class, id);
	}

	/**
	 * @param user
	 * @return
	 */
	public Mono<User> save(User user) {
		return repository.save(user);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return 
	 */
	public void deleteResource(Long id) {
		repository.delete(repository.findOne(User.class, id));
	}
	
	/**
	 * @param user
	 * @return
	 */
	public Mono<User> updateResource(User user) {
		return repository.update(user);
	}

}
