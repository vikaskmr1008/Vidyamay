package com.vidyamay.dataaccess;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
 * @author vikas.kumar3
 *
 */
public class DataAccessFactory { 
	
	/**
	 * 
	 */
	private static final PersistenceManager PERSISTENCE_MANAGER = JDOHelper
			.getPersistenceManagerFactory("Vidyamay").getPersistenceManager();

	/**
	 * @return
	 */
	public static PersistenceManager getPersistenceManager() {
		return PERSISTENCE_MANAGER;
	}
	
	/**
	 * 
	 */
	private static final PersistenceManagerFactory PERSISTENCE_MANAGER_FACTORY = JDOHelper.getPersistenceManagerFactory("Vidyamay");

	/**
	 * @return
	 */
	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		return PERSISTENCE_MANAGER_FACTORY;
	}
}
