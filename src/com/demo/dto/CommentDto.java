package com.demo.dto;

public final class CommentDto extends PageDto{
	// 自增id
	private long id;
	// 评论者id
	private int owner_id;
	// 评论
	private String owner_comment;
	// 生成时间
	private String ct;
	// 评论的评论id
	private String pre_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public String getOwner_comment() {
		return owner_comment;
	}

	public void setOwner_comment(String owner_comment) {
		this.owner_comment = owner_comment;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getPre_id() {
		return pre_id;
	}

	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
	}

}
