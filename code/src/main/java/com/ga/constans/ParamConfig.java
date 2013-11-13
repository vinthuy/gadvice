/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.constans;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @description 参数配置
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see     
 * @since   girladvice1.0
 */
public class ParamConfig {
	
	public static String test = null;
	//静态加载
	static{
		getConfig();
	}
	
	/**
	 * @description 初始化操作
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @return 
	 * @since gadvice1.0.0
	 */
	public static Properties getProperties() {
		Properties props = new Properties();
		try {
			InputStream in = ParamConfig.class
					.getResourceAsStream("/paramConfig.properties");
			props.load(in);
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @description 获取配置信息
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>  
	 * @since gadvice1.0.0
	 */
	public static void getConfig() {
		Properties props = getProperties();

	}
	
	/**
	 * @description 从配置信息中获取字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param key
	 * @return 
	 * @since gadvice1.0.0
	 */
	@SuppressWarnings("unused")
	private String getStringFromConfig(Properties props,String key){
		return props.get(key).toString();
	}
	
	/**
	 * @description 从配置中获取INT型
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param props
	 * @param key
	 * @return 
	 * @since gadvice1.0.0
	 */
	@SuppressWarnings("unused")
	private int getIntFromConfig(Properties props,String key){
		return Integer.parseInt(props.get(key).toString());
	}
	
	/**
	 * @description 从参数中获取值
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param props
	 * @param key
	 * @param arguments
	 * @return 
	 * @since gadvice1.0.0
	 */
	@SuppressWarnings("unused")
	private String getStringFromConfigHasParam(Properties props,String key,Object...arguments){
		String msg = props.getProperty(key);
		return MessageFormat.format(msg, arguments);
	}
	
}
