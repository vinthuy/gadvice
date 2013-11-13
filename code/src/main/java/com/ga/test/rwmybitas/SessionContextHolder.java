/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-3
 */

package com.ga.test.rwmybitas;

/**
 * @description 数据库连接切换类
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-3
 * @see
 * @since gadvice1.0
 */
public class SessionContextHolder {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSessionType(String dataSessionType) {
		contextHolder.set(dataSessionType);
	}

	public static String getDataSessionType() {
		return (String) contextHolder.get();
	}

	public static void clearDataSessionType() {
		contextHolder.remove();
	}
}
