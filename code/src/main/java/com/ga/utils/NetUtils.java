
package com.ga.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @description 网络工具
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-8-26
 * @see     
 * @since   social-commons1.0
 */
public class NetUtils {

	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * @description 获取本机IP
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @return
	 * @throws Exception 
	 * @since girladvice1.0
	 */
	public static String getIp() throws Exception{
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress().toString();
	}
	
	/**
	 * @description 发送HTTP GET请求
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param httpUrl
	 * @return
	 * @throws Exception
	 * @since girladvice1.0
	 */
	public static String httpGet(String httpUrl) throws Exception {
		return httpGet(httpUrl, "", DEFAULT_CHARSET);
	}
	
	/**
	 * @description 发送HTTP GET请求
	 * @author <a href="mailto:bin.du@daw.so">杜斌</a> 
	 * @param httpUrl URL请求主域部分的字符串
	 * @param parameters 已拼装好了的参数部分的字符串
	 * @param encoding 字符集编码
	 * @return
	 * @throws IOException 
	 * @since girladvice1.0
	 */
	public static String httpGet(String httpUrl, String parameters, String encoding) throws IOException {
		URLConnection connection = null;
		BufferedReader reader = null;
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(httpUrl + StringUtils.safeString(parameters));
			connection = url.openConnection();
			connection.connect();
			reader = StringUtils.isNotNullOrBlank(encoding) ?  
					new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding.trim())) :
						new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lineContent = null;
			while ((lineContent = reader.readLine()) != null)
				result.append(lineContent).append("\n");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		return result.toString();
	}

	/**
	 * @description 发送HTTP POST请求
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param httpUrl
	 * @param param
	 * @return
	 * @throws Exception
	 * @since girladvice1.0
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static String httpPost(String httpUrl, Map param) throws IOException {
		Set<Entry<String, Object>> set = param.entrySet();
		StringBuffer parameters = new StringBuffer();
		Entry<String, Object> entry = null;
		for (Iterator<Entry<String, Object>> it = set.iterator(); it.hasNext();) {
			entry = it.next();
			Object value = entry.getValue();
			if(value!=null)
			parameters.append(entry.getKey()).append("=").append(entry.getValue()).append(it.hasNext() ? "&" : "");
		}
		return httpPost(httpUrl, parameters.toString(), DEFAULT_CHARSET);
	}
	
	/**
	 * @description 发送HTTP POST请求
	 * @author <a href="mailto:bin.du@daw.so">杜斌</a> 
	 * @param httpUrl : URL请求主域部分的字符串
	 * @param parameters : 已拼装好了的参数部分的字符串
	 * @param encoding : 字符集编码
	 * @return
	 * @throws Exception 
	 * @since girladvice1.0
	 */
	public static String httpPost(String httpUrl, String parameters, String encoding) throws IOException {
		
		HttpURLConnection connection = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			if (parameters != null) {
				writer = StringUtils.isNotNullOrBlank(encoding) ? 
						new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), encoding.trim())) : 
							new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				writer.write(parameters);
				writer.flush();
			}
			reader = StringUtils.isNotNullOrBlank(encoding) ? 
						new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding.trim())) : 
							new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lineContent = "";
			while ((lineContent = reader.readLine()) != null)
				result.append(lineContent).append("\n");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			try {
				if (writer != null)
					writer.close();
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException(e);
			} finally {
				if (connection != null)
					connection.disconnect();
			}
		}
		return result.toString();
	}
	
}
