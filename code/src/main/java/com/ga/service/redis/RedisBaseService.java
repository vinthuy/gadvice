/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.service.redis;

import java.util.Set;

/**
 * @description redis基础类
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @param <V>
 * @param <K>
 * @see     
 * @since   girladvice1.0
 */
public interface RedisBaseService<V, K> {


	/**
	 * @description get命令
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param key
	 * @return 
	 * @since girladvice1.0.0
	 */
	public Object get(K key);

	/**
	 * @description get命令
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param keys
	 * @return 
	 * @since girladvice1.0.0
	 */
	public Set<V> get(K[] keys);
}
