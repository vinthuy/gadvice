
package com.ga.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description 加密工具类
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see     
 * @since   girladvice1.0
 */
public class EncryptUtils {
	
	/**
	 * @description 提供小写32位MD2和MD5，40位SHA和SHA1，
	 * 				以及64位SHA1-256等常用加密方法
	 * @author <a href="mailto:bin.du@daw.so">杜斌</a> 
	 * @param sourceContent
	 * @param algorithm
	 * @return 
	 * @since girladvice1.0
	 */
	public static String messageDigestEncrypt(String sourceContent, String algorithm) {
		String encryptContent = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			encryptContent = new String(sourceContent);
			byte[] bytes = messageDigest.digest(encryptContent.getBytes());
			StringBuffer buffer = new StringBuffer(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				if ((bytes[i] & 0xff) < 0x10) 
					buffer.append("0");
				buffer.append(Long.toString(bytes[i] & 0xff, 16));
			}
			encryptContent = buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptContent;
	}
				
}
