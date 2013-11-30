
 /*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-19
 */

package com.ga.dao;

/**
 * @description [动态]数据库操作接口
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-19
 * @see     
 * @since   ga1.0
 */
import java.util.List;

import com.ga.domain.Trends;
import com.ga.domain.TrendsToUserlist;
import com.ga.dto.TrendsQueryBuilder;


/**
 * @description [动态]数据操作映射接口
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-19
 * @see     
 * @since   ga1.0
 */

public interface TrendsDao {
	/**
	 * @description 插入一条[动态]数据到数据库
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param t
	 * @return 
	 * @since ga1.0.0
	 */
	public int insertOneTrends(Trends t);
	
	/**
	 * @description 插入一条[动态评论转发用户]数据到数据库
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param ttu
	 * @return 
	 * @since ga1.0.0
	 */
	public int insertOneTrendsToUserlist(TrendsToUserlist ttu);
	
	/**
	 * @description 根据查询条件查询[动态]数据列表
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param tqb
	 * @return 
	 * @since ga1.0.0
	 */
	public List<Trends> selectTrendsByQuery(TrendsQueryBuilder tqb);
	
	/**
	 * @description 查询数据库中符合条件的[动态]条目数
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param tqb
	 * @return 
	 * @since ga1.0.0
	 */
	public int selectTrendsCountByQuery(TrendsQueryBuilder tqb);
	
	/**
	 * @description 根据条件查询符合条件的[动态评论转发用户]数据
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param tqb
	 * @return 
	 * @since ga1.0.0
	 */
	public List<TrendsToUserlist> selectTrendsToUserlistByQuery(TrendsQueryBuilder tqb);
	
	/**
	 * @description 根据条件查询符合条件的[动态评论转发用户]条目数
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param tqb
	 * @return 
	 * @since ga1.0.0
	 */
	public int selectTrendsToUserlistCountByQuery(TrendsQueryBuilder tqb);
}
