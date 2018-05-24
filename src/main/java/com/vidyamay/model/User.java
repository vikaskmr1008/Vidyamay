/**
 * 
 */
package com.vidyamay.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Vidyamay Education Pvt Ltd.
 *
 */
@PersistenceCapable
public class User extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 852827404728980443L;
	
	@PrimaryKey
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

}
