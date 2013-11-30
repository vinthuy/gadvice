/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.service.impl.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.ga.service.redis.CacheService;

public class CacheServiceImpl<K, V> implements CacheService<K, V> {

	private RedisTemplate<K, V> redisTemplate;

	private int dbindex;

	public void setDbindex(int dbindex) {
		this.dbindex = dbindex;
	}

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public V get(final String key, Class<V> classz) {
		final RedisSerializer<String> keySerializer = redisTemplate
				.getStringSerializer();
		final RedisSerializer<V> objSerializer = new JacksonJsonRedisSerializer<V>(
				classz);
		return redisTemplate.execute(new RedisCallback<V>() {
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(dbindex);
				return objSerializer.deserialize(connection.get(keySerializer
						.serialize(key)));
			}
		});
	}

	@Override
	public void set(final String key, final V value, Class<V> classz) {
		final RedisSerializer<String> keySerializer = redisTemplate
				.getStringSerializer();
		final RedisSerializer<V> objSerializer = new JacksonJsonRedisSerializer<V>(
				classz);
		redisTemplate.execute(new RedisCallback<V>() {
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(dbindex);
				byte[] keyBytes = keySerializer.serialize(key);
				connection.set(keyBytes, objSerializer.serialize(value));
				return null;
			}
		});
	}

}
