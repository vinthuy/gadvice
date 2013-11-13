/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-7
 */

package com.ga.test;

import com.ga.test.annotation.MappingUrl;

/**
 * @description
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-7
 * @see
 * @since gadvice1.0
 */
public class Test {
	@MappingUrl("test1")
	public void test1(String name, String pass) {

	}
	@MappingUrl("test2")
	public void test2(String name, Param param) {
		
	}

}
