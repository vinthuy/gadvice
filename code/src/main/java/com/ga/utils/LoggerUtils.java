
package com.ga.utils;

import java.util.Date;

import org.apache.log4j.Logger;

public class LoggerUtils {
	
	/**
	 * @description 打印异常信息
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a> 
	 * @param logger
	 * @param e 
	 * @since girladvice1.0.0
	 */
	public static void printException(Logger logger, Exception e) {

		StackTraceElement[] error = e.getStackTrace();
		StringBuffer sb = new StringBuffer(DateUtils.dateToString(new Date())
				+ "发生异常：" + e.getClass().getName() + "\n异常信息为：");
		for (StackTraceElement stackTraceElement : error) {
			sb.append(stackTraceElement.toString() + "\n");
		}
		logger.info(sb.toString());
	}
}
