package com.ga.domain;

public class TrendsToUserlist {

	private int id;
	private String trendId;//
	private String userList;//动态用户列表List类型（里面包括userId,UserType）
	private int type;//类型 1.评论 2.转发
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrendId() {
		return trendId;
	}
	public void setTrendId(String trendId) {
		this.trendId = trendId;
	}
	public String getUserList() {
		return userList;
	}
	public void setUserList(String userList) {
		this.userList = userList;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
