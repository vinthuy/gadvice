/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-9-17
 */

package com.ga.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.TestDao;
import com.ga.domain.Test;
import com.ga.exception.BizException;
import com.ga.service.TestService;
import com.ga.service.redis.CacheService;

/**
 * @description
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-9-17
 * @see
 * @since girladvice1.0
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	@Autowired
	private CacheService<String, Test> testCacheService;

	/**
	 * @description
	 * @author <a href="mailto:bin.du@saw.so">胡瑞永</a>
	 * @param name
	 * @since girladvice1.0.0
	 */
	public void insert(String name) throws BizException {
		if (name == null)
			throw new BizException("参数不能为空");
		testDao.insert(name);
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public void testCache(String name) {
		Test test = new Test();
		test.setId(2);
		test.setName("123");
		testCacheService.set("" + test.getId(), test, Test.class);
		Test test1 = testCacheService.get("" + test.getId(), Test.class);
		System.out.println(test1.getName());

	}

}
