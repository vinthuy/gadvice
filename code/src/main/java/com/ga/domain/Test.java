/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-9-17
 */

package com.ga.domain;

import java.io.Serializable;

/**
 * @description test demain
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-9-17
 * @see     
 * @since   girladvice1.0
 */
public class Test implements Serializable{
	private static final long serialVersionUID = 1L;
	/**id*/
private int id;
	/**name*/
private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
