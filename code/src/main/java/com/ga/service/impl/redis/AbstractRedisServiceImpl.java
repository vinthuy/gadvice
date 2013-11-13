/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.service.impl.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.ga.constans.RedisConfig;
import com.ga.service.redis.RedisBaseService;

/**
 * @description redis基本类
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @param <V>
 * @param <K>
 * @see     
 * @since   girladvice1.0
 */
public abstract class AbstractRedisServiceImpl<V, K> implements RedisBaseService<V, K> {
	
	@Autowired
	protected RedisTemplate<K, V> redisTemplate;

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * @description set值到默认数据库
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param key
	 * @param value 
	 * @since girladvice1.0.0
	 */
	@SuppressWarnings("unchecked")
	public void set(final K key, final V value) {
		final RedisSerializer<Object> keySerializer = (RedisSerializer<Object>) redisTemplate.getKeySerializer();
		final RedisSerializer<Object> objSerializer = new JacksonJsonRedisSerializer<Object>(Object.class);			
		redisTemplate.execute(new RedisCallback<V>() {
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(RedisConfig.default_db);
				byte[] keyBytes = keySerializer.serialize(key);
				connection.set(keyBytes, objSerializer.serialize(value));
				return null;
			}
		});
	}
	
	/**  
	 * @description 实现从get中取得一个实体
	 * @author <a href="mailto:bin.du@saw.so">胡瑞永</a> 
	 * @param key
	 * @return 
	 * @since girladvice1.0.0
	 */
	public Object get(final K key) {
		@SuppressWarnings("unchecked")
		final RedisSerializer<Object> keySerializer = (RedisSerializer<Object>) redisTemplate.getKeySerializer();
		final RedisSerializer<Object> objSerializer = new JacksonJsonRedisSerializer<Object>(Object.class);
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(RedisConfig.default_db);
				return objSerializer.deserialize(connection.get(keySerializer.serialize(key)));
			}
		});
	}
}
