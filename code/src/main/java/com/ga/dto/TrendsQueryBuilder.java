package com.ga.dto;

public class TrendsQueryBuilder extends BaseQueryBuilder{

	private Integer id;//查询用主键ID
	private Integer trendsType;//动态类型 1.普通 2.求知
	private Integer userId;//用户ID
	private Integer userType;//
	private String trendId;//动态UUID
	private Integer toType;//动态转发类型 1.评论 2.转发
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTrendsType() {
		return trendsType;
	}
	public void setTrendsType(Integer trendsType) {
		this.trendsType = trendsType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getToType() {
		return toType;
	}
	public void setToType(Integer toType) {
		this.toType = toType;
	}
	public String getTrendId() {
		return trendId;
	}
	public void setTrendId(String trendId) {
		this.trendId = trendId;
	}
}
