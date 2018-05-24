package com.vidyamay.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.vidyamay.config.PMFConfig;
import com.vidyamay.model.User;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository{
	
	/** The pm. */
	PersistenceManager pm = PMFConfig.getPersistenceManagerFactory().getPersistenceManager();

	/* (non-Javadoc)
	 * @see com.vidyamay.dao.UserRepository#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		Query query = pm.newQuery(User.class);
		return (List<User>) query.execute();
	}

	/* (non-Javadoc)
	 * @see com.vidyamay.dao.UserRepository#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		return pm.getObjectById(User.class, id);
	}

	/* (non-Javadoc)
	 * @see com.vidyamay.dao.UserRepository#save(com.vidyamay.model.User)
	 */
	@Override
	public User save(User user) {
		return pm.makePersistent(user);
	}

}
