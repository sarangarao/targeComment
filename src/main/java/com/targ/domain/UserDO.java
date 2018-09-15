package com.targ.domain;

import java.util.Collection;

public class UserDO {

	
	  private Long id;
	  
	  private String email;
	  
	  private String password;
	  private String username;
	  
	  private String name;
	  private String lastName;
	  
	  private int active;
	 

	  private Collection<PostDO> posts;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public Collection<PostDO> getPosts() {
		return posts;
	}


	public void setPosts(Collection<PostDO> posts) {
		this.posts = posts;
	}
	  
	  
	  
	  
}
