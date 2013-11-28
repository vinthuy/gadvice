package com.ga.utils;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @description JSON解析工具类
 * @author  <a href="mailto:bin.du@daw.so">杜斌</a>
 * @version 1.0, 2013-8-23
 * @see     
 * @since   social-commons1.0
 */
public class JSONUtils {

	/**
	 * @description 将对象转换为JSOn
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param obj
	 * @return 
	 * @since girladvice1.0.0
	 */
	public static String objectToString(Object obj){  
		JSONObject jsonObject = new JSONObject(obj);
		String jsonResult = jsonObject.toString();
		return jsonResult;
	}
	
	public static Object jsonToObject(String str){
		return JSONObject.stringToValue(str);
	}
	
	/**
	 * @description 将数组对象转换为JSON字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param array
	 * @return
	 * @throws JSONException 
	 * @since girladvice1.0.0
	 */
	public static String arrayToJsonString(Object[] array) throws JSONException{
		JSONArray jsonArray = new JSONArray(array);
		StringBuffer jsonResult = new StringBuffer();
		int length = jsonArray.length();
		if (length > -1) {
			if (length == 1) 
				jsonResult.append(objectToString(jsonArray.get(0)));
			else {
				jsonResult.append("[");
				for (int i = 0; i < length - 1; i++) {
					jsonResult.append(objectToString(jsonArray.get(i)));
					jsonResult.append(",");
				}
				jsonResult.append(objectToString(jsonArray.get(length - 1)));
				jsonResult.append("]");
			}
		}
		return jsonResult.toString();
	}
	
	/**
	 * @description 将集合对象转换成JSON字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param collection
	 * @return
	 * @throws JSONException 
	 * @since girladvice1.0.0
	 */
	public static String collectionToJsonString(Collection<?> collection) throws JSONException {
		return arrayToJsonString(collection.toArray());
	}
	
}
