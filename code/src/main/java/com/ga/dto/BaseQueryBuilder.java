package com.ga.dto;

import com.ga.constans.Constans;

public class BaseQueryBuilder {

	protected int size = Constans.DATABASE_QUERY_SIZE;
	protected int start = 0;
	protected int page = 1;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStart() {
		if(page <= 1)
			start = 0;
		else{
			start = (page - 1) * size;
		}
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
