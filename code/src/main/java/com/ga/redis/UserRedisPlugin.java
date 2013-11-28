/*
 * Copyright (c) Joygame 2013
 * <a href="www.cmge.com">中国手游</a>
 * Create Date : 2013-10-25
 */

package com.ga.redis;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.ga.utils.CollectionUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description
 * @author <a href="mailto:ruiyong@daw.so">胡瑞永</a>
 * @version 1.0, 2013-10-25
 * @see
 * @since social-chatroom1.0
 */
public class UserRedisPlugin {

	public static int redis_db_user = 1;
	public static int redis_db_friend = 2;
	public static String host = null;
	private static int port = 0;
	private static int timeout = 0;
	private static int maxActive = 0;
	private static int maxIdle = 100;
	private static int maxWait = 1000;
	private static boolean testOnBorrow = true;

	/**
	 * @description 初始化预读信息
	 * @author <a href="mailto:ruiyong.hu@daw.so">胡瑞永</a>
	 * @return
	 * @since social-commons1.0.0
	 */
	private static Properties getProperties() {
		Properties props = new Properties();
		try {
			InputStream is = UserRedisPlugin.class.getResourceAsStream("/application.properties");
			System.out.println("is null:" + (is == null));
//			InputStream is = ClassLoader.getSystemResourceAsStream("application.properties");
			props.load(is);
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description 获取配置信息
	 * @author <a href="mailto:ruiyong.hu@daw.so">胡瑞永</a>
	 * @since social-commons1.0.0
	 */
	private static void getConfig() {
		Properties props = getProperties();
		host = props.get("redis.host").toString();
		port = Integer.parseInt(props.get("redis.port").toString());
		timeout = Integer.parseInt(props.get("redis.timeout").toString());
		maxActive = Integer.parseInt(props.get("redis.maxActive").toString());
		maxIdle = Integer.parseInt(props.get("redis.maxIdle").toString());
		maxWait = Integer.parseInt(props.get("redis.maxWait").toString());
		testOnBorrow = Boolean.parseBoolean(props.get("redis.testOnBorrow")
				.toString());
		redis_db_user = Integer.parseInt(props.get("redis_db_user").toString());
		redis_db_friend = Integer.parseInt(props.get("redis_db_friend")
				.toString());
	}

	static {
		getConfig();
	}
	private static JedisPool pool = null;

	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
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
			pool = new JedisPool(config, host, port, timeout);
		}
		return pool;
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;

		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}

		return value;
	}


	public static List<String> hmget(String key,int redisdb,
			String[] feilds) {
		List<String> list = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.select(redisdb);
			list = jedis.hmget(key, feilds);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return list;
	}
	
	public static List<byte[]> hmget(byte[] key,int redisdb,
			byte[][] feilds) {
		List<byte[]> list = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.select(redisdb);
			list = jedis.hmget(key, feilds);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return list;
	}
	public static byte[] hget(byte[] key,int redisdb,
			byte[] feild) {
		byte[] value = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.select(redisdb);
			value = jedis.hget(key, feild);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}
	
	public static List<String> mget(int redisdb,
			String[] feilds) {
		List<String> list = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.select(redisdb);
			list = jedis.mget(feilds);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return list;
	}
	
	public static Set<String> smembers(int redisdb,
			String key) {
		Set<String> list = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.select(redisdb);
			list = jedis.smembers(key);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return list;
	}
	
	public static void main(String[] args) {
		List list = hmget("buser".getBytes(), redis_db_user, new byte[][]{"1".getBytes()});
//		System.out.println(list[0]);
//		Set set = smembers(6, "4");
//		byte[][] userField = {"1".getBytes(),"4".getBytes(),"5".getBytes()};
//		Map<String,Object> rs = getUserFriendDetail(RedisConfigConstans.USER_REDIS_NAME.getBytes(),
//								userField, Integer.toString(1));
//		System.out.println(rs);
	}
	
	public static Map<String,Object> getUserFriendDetail(byte[] userKey,byte[][] userFeilds,String sdkUserPriId){
		List<byte[]> userList = null;
		Map<String,Object> ufrRalationList = new HashMap<String,Object>();
		JedisPool pool = null;
		Jedis jedis = null;
		Map<String,Object> remap = new HashMap<String, Object>();
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.select(redis_db_user);
			userList = jedis.hmget(userKey, userFeilds);
			if(CollectionUtils.isNull(userList))
			{
				return null;
			}
			jedis.select(redis_db_friend);
			Set<String> frList = jedis.smembers(sdkUserPriId);
			for(String fr:frList)
			{
				ufrRalationList.put(fr, 1);
			}
			remap.put("userList", userList);
			remap.put("ufrRalationList", ufrRalationList);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return remap;
	}
}
