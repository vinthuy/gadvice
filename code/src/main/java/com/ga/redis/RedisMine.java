
 /*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-22
 */

package com.ga.redis;

import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @description Redis
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-22
 * @see     
 * @since   ga1.0
 */

public class RedisMine {

	private static JedisPool jedisPool;
	
	public static JedisPool getPool(){
		if(jedisPool == null)
			jedisPool = new RedisMine().instance();
		return jedisPool;
	}
	public JedisPool instance(){
		Properties props = new Properties();
		try {
			InputStream is = UserRedisPlugin.class.getResourceAsStream("/application.properties");
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String host = props.get("redis.host").toString();
		int port = Integer.parseInt(props.get("redis.port").toString());
		int timeout = Integer.parseInt(props.get("redis.timeout").toString());
		int maxActive = Integer.parseInt(props.get("redis.maxActive").toString());
		int maxIdle = Integer.parseInt(props.get("redis.maxIdle").toString());
		int maxWait = Integer.parseInt(props.get("redis.maxWait").toString());
		boolean testOnBorrow = Boolean.parseBoolean(props.get("redis.testOnBorrow")
				.toString());
		JedisPoolConfig config = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxActive(maxActive);
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(maxIdle);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWait(maxWait);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(testOnBorrow);
		return new JedisPool(config, host, port, timeout, "liyang");
	}
	
	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null && pool != null) {
			pool.returnResource(redis);
		}
	}
}
