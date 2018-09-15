package com.targ.domain;

import java.util.Date;

public class CommentDO {


	private Long id;
	private String body;
	private Date createDate;

	private PostDO post;
	private UserDO user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public PostDO getPost() {
		return post;
	}
	public void setPost(PostDO post) {
		this.post = post;
	}
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user) {
		this.user = user;
	}
	
	


}
