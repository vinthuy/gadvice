
package com.ga.utils;
import java.util.Collection;
/**
 * @description 判断集合是否为空
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see     
 * @since   girladvice1.0
 */
public class CollectionUtils {
	
	
	public static boolean isNullOrEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}
	
	public static boolean isNotNullOrEmpty(Collection<?> collection) {
		return !isNullOrEmpty(collection);
	}

}
