package com.vidyamay.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.jdo.annotations.PersistenceAware;

import org.datanucleus.api.jdo.annotations.CreateTimestamp;
import org.datanucleus.api.jdo.annotations.CreateUser;
import org.datanucleus.api.jdo.annotations.UpdateTimestamp;
import org.datanucleus.api.jdo.annotations.UpdateUser;

/**
 * @author Vidyamay Education Pvt Ltd.
 */
@PersistenceAware
public class BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8335068094303841086L;
	
	@CreateTimestamp
    Timestamp createTimestamp;

    @CreateUser
    String createUser;

    @UpdateTimestamp
    Timestamp updateTimestamp;

    @UpdateUser
    String updateUser;

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
