/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-9-17
 */

package com.ga.service;

import com.ga.exception.BizException;
import com.ga.service.redis.CacheService;

/**
 * @description
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-9-17
 * @param <V>
 * @param <K>
 * @see     
 * @since   girladvice1.0
 */
public interface TestService{

	/** 
	 * @description 插入
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param name 
	 * @since girladvice1.0.0
	 */
	public void insert(String name) throws BizException;

	public void testCache(String name) ;

}
