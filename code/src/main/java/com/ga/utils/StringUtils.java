
package com.ga.utils;

import java.util.UUID;

/**
 * @description 字符串工具类
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-8-21
 * @see     
 * @since   girladvice1.0
 */
public class StringUtils {
	
	/**
	 * @description 判断是否为空字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * @description 判断是否不为空字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static boolean isNotNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}
	
	/**
	 * @description 判断是否为空白字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static boolean isNullOrBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * @description 判断是否不为空白字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static boolean isNotNullOrBlank(String str) {
		return !isNullOrBlank(str);
	}
	
	/**
	 * @description 字符串首字符大写
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * @description 字符串首字符小写
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/**
	 * @description 字符串首字符大/小写
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @param capitalize
	 * @return 
	 * @since girladvice1.0.0
	 */
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (isNullOrEmpty(str))
			return str;

		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize)
			buf.append(Character.toUpperCase(str.charAt(0)));
		else
			buf.append(Character.toLowerCase(str.charAt(0)));

		buf.append(str.substring(1));
		return buf.toString();
	}
	
	/**
	 * @description 返回不为null的字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String safeString(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * @description 获取后缀之前的字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str 
	 * @param suffix
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String beforeSuffix(String str, String suffix) {
		if (str == null || suffix == null)
			return str;
		if (!str.endsWith(suffix) || str.equals(suffix)) 
			return "";
		int index = str.lastIndexOf(suffix);
		return str.substring(0, index);
	}
	
	/**
	 * @description 获取前缀之后的字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @param prefix
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String afterPrefix(String str, String prefix) {
		if (str == null || prefix == null)
			return str;
		if (!str.startsWith(prefix) || str.equals(prefix)) 
			return "";
		int index = str.indexOf(prefix);
		return str.substring(index + prefix.length(), str.length());
	}
	
	/**
	 * @description 获取第一个片段之前的字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @param part
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String beforeFristPart(String str, String part) {
		if (str == null || part == null)
			return str;
		int index = str.indexOf(part);
		return index > 0 ? str.substring(0, index) : "";
	}
	
	/**
	 * @description 获取第一个片段之后的字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @param part
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String afterFristPart(String str, String part) {
		if (str == null || part == null)
			return str;
		int index = str.indexOf(part);
		return index != -1 ? str.substring(index + part.length()) : "";
	}
	
	/**
	 * @description 获取最后一个片段之前的字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @param part
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String beforeLastPart(String str, String part) {
		if (str == null || part == null)
			return str;
		int index = str.lastIndexOf(part);
		return index > 0 ? str.substring(0, index) : "";
	}
	
	/**
	 * @description 获取最后一个片段之后的字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str 
	 * @param part
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String afterLastPart(String str, String part) {
		if (str == null || part == null)
			return str;
		int index = str.lastIndexOf(part) + part.length();
		return index < str.length() ? str.substring(index, str.length()) : "";
	}
	
	/**
	 * @description 清空字符串中所有的空格、制表符和换页符等空白字符
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param str
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String trimAll(String str) {
		return safeString(str).replaceAll("\\s*", "");  
	}
	
	/**
	 * @description 生成32位UUID
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();  
	}
	
}
