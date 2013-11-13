/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-3
 */

package com.ga.test.rwmybitas;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description 实现读写分离接口
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-3
 * @see     
 * @since   gadvice1.0
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

	/**
	 * @description 返回可用的session key
	 * @author <a href="mailto:bin.du@saw.so">胡瑞永</a> 
	 * @return 
	 * @since gadvice1.0.0 
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return SessionContextHolder.getDataSessionType();
	}

}
