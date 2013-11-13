/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-9-17
 */

package com.ga.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.TestDao;
import com.ga.exception.BizException;
import com.ga.service.TestService;

/**
 * @description
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-9-17
 * @see     
 * @since   girladvice1.0
 */
@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDao testDao;
	/**
	 * @description
	 * @author <a href="mailto:bin.du@saw.so">胡瑞永</a> 
	 * @param name 
	 * @since girladvice1.0.0 
	 */
	public void insert(String name) throws BizException{
		if(name == null)
		throw new BizException("参数不能为空");
		testDao.insert(name);
	}
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

}
