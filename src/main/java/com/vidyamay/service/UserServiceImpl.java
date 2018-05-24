package com.vidyamay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidyamay.dao.UserRepository;
import com.vidyamay.model.User;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userService;

	/* (non-Javadoc)
	 * @see com.vidyamay.service.UserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return userService.findAll();
	}

	/* (non-Javadoc)
	 * @see com.vidyamay.service.UserService#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		return userService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.vidyamay.service.UserService#save(com.vidyamay.model.User)
	 */
	@Override
	public User save(User user) {
		return userService.save(user);
	}

}
