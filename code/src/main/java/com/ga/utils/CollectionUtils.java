package com.ga.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description 判断集合是否为空
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see
 * @since girladvice1.0
 */
public class CollectionUtils {

	public static boolean isNullOrEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}

	public static boolean isNotNullOrEmpty(Collection<?> collection) {
		return !isNullOrEmpty(collection);
	}

	/**
	 * @description 判断List是否为空
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param list
	 * @return 
	 * @since ga1.0.0
	 */
	public static boolean isNull(List<?> list) {
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				if (obj != null)
					return false;
			}
		}
		return true;
	}

	/**
	 * @description 判断集合是否为空
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param list
	 * @return 
	 * @since ga1.0.0
	 */
	public static boolean isNull(Set<?> list) {
		if (list != null && list.size() != 0) {
			for (Object obj : list) {
				if (obj != null)
					return false;
			}
		}
		return true;
	}

	/**
	 * @description 判断MAP是否为空
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param list
	 * @return 
	 * @since ga1.0.0
	 */
	public static boolean isNull(Map<String, Object> map) {
		if (map != null && map.size() != 0 && !map.isEmpty()) {
			Set<Map.Entry<String, Object>> sets = map.entrySet();
			for (Map.Entry<String, Object> entry : sets) {
				if (entry != null && entry.getValue() != null) {
					return false;
				}
			}
		}
		return true;
	}
}
