package com.ga.service.impl;

import java.nio.charset.Charset;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

/**
 * @description json序列化工具
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013年11月30日
 * @see
 * @since ga1.0
 */
@Component
public class JsonObjectUtils {
	public static final String EMPTY_JSON = "{}";
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	protected ObjectMapper objectMapper = new ObjectMapper();

	public JsonObjectUtils() {
	}

	public String seriazileAsString(Object object) {
		if (object == null) {
			return EMPTY_JSON;
		}
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch (Exception ex) {
			throw new SerializationException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}

	public <T> T deserializeAsObject(String str, Class<T> clazz) {
		if (str == null || clazz == null) {
			return null;
		}
		try {
			return this.objectMapper.readValue(str, clazz);
		} catch (Exception ex) {
			throw new SerializationException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}
}
