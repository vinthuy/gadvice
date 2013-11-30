
 /*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-19
 */

package com.ga.service;

import java.util.List;

import com.ga.domain.Trends;
import com.ga.web.model.ResultModel;


/**
 * @description [动态]数据操作接口
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-19
 * @see     
 * @since   ga1.0
 */

public interface TrendsService {

	/**
	 * @description 插入一条[动态]数据到数据库
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param trends
	 * @return 
	 * @since ga1.0.0
	 */
	public ResultModel<Object> insertOneTrends(Trends trends);
	
	/**
	 * @description 获取用户最近的指定条数的动态列表
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param count 动态条数
	 * @param userId 用户ID
	 * @return 
	 * @since ga1.0.0
	 */
	public List<Trends> findRecentTrends(int count, int userId);
}
