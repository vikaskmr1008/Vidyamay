package com.vidyamay.config;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * @author jangfateh.singh
 *
 */
public final class PMFConfig {

	private static final PersistenceManagerFactory PERSISTENCE_MANAGER_FACTORY = JDOHelper
			.getPersistenceManagerFactory("PersistenceUnit");

	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		return PERSISTENCE_MANAGER_FACTORY;
	}
}