
 /*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-20
 */

package com.ga.redis;


/**
 * @description 定义Redis一些静态的常用的变量及方法
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-20
 * @see     
 * @since   ga1.0
 */

public class RedisCommon {

	public final static String REDIS_TRENDS_USER_STORE_PREFFIX = "redis_trends_user_store_";//保存到redis中的用户动态的key前缀，通过在前缀后面加用户ID查找
	
	public final static int REDIS_TRENDS_USER_STORE_COUNT = 10;//默认redis保存用户的动态条数
}
