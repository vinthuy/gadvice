/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.service.redis;

public interface CacheService<K, V> {

	public V get(final String key, Class<V> classz);

	public void set(final String key, final V value, Class<V> classz);
}
