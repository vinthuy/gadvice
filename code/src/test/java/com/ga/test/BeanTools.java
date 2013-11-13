/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-7
 */

package com.ga.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ga.test.annotation.MappingUrl;

public class BeanTools {
	public static Map<String, Object> paramMap = new HashMap<String, Object>();

	public static void getRelect(Object obj) {
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method method : methods) {
			
			System.out.println(method.getName());
			MappingUrl mappingUrl = (MappingUrl)method.getAnnotation(MappingUrl.class);  
			if(mappingUrl!=null)
			{
				//获取参数类型	Class<?>[] paramClass = method.getParameterTypes();
				Map<String, Object> paramNames =  Classes.getMethodParamNames(method);
				System.out.println(paramNames);
				paramMap.put(mappingUrl.value(),paramNames);
			}
			
		}
	}

	public static void main(String[] args) { 
		Test t = new Test();
		getRelect(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pass", "pass");
		map.put("name", "name");
		int i = 9;
	}
	
	public boolean ContainParam(String methodName,String paramName){
		Map<String,Object> valueKeyMap = (Map<String, Object>) paramMap.get(methodName);
		if(valueKeyMap.containsKey(paramName))
		{
			return true;
		}
		else{
			Set<Entry<String, Object>> entries = valueKeyMap.entrySet();
		}
		return false;
	}
	
	public  List<Class<?>> getBasicJavaType(){
		List<Class<?>> lst =new  ArrayList<Class<?>>();
		lst.add(String.class);
		lst.add(Integer.class);
		lst.add(Double.class);
		return lst;
	}
}
