
 /*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-19
 */

package com.ga.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.ga.constans.ResultCode;
import com.ga.dao.TrendsDao;
import com.ga.domain.Trends;
import com.ga.dto.TrendsQueryBuilder;
import com.ga.redis.RedisCommon;
import com.ga.redis.RedisMine;
import com.ga.service.TrendsService;
import com.ga.utils.StringUtils;
import com.ga.web.model.ResultModel;
import com.google.gson.Gson;


/**
 * @description [动态]数据操作实现类
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-19
 * @see     
 * @since   ga1.0
 */

@Service
public class TrendsServiceImpl implements TrendsService{

	@Autowired
	private TrendsDao trendsDao;
	
	public void setTrendsDao(TrendsDao trendsDao) {
		this.trendsDao = trendsDao;
	}


	@Override
	public ResultModel<Object> insertOneTrends(Trends trends) {
		ResultModel<Object> resultModel = new ResultModel<Object>();
		JedisPool jp = RedisMine.getPool();
		Jedis jedis = jp.getResource();
		String uuids = UUID.fromString(StringUtils.transToUUIDString(trends.getContent() + System.currentTimeMillis())).toString();
		trends.setId(uuids);
		trends.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int result = trendsDao.insertOneTrends(trends);
		if(result > 0){
			String ukey = RedisCommon.REDIS_TRENDS_USER_STORE_PREFFIX + trends.getUserId();//组装redis获取用户动态的key
			long cu = jedis.llen(ukey);
			if(cu > 10)
				jedis.lpop(ukey);
			jedis.rpush(ukey, new Gson().toJson(trends));
			RedisMine.returnResource(jp, jedis);
			resultModel.setCode(ResultCode.SUCCESS);
		} else {
			resultModel.setCode(ResultCode.FAILED);
			resultModel.setMessage("添加数据失败");
		}
		return resultModel;
	}


	@Override
	public List<Trends> findRecentTrends(int count, int userId) {
		List<Trends> tList = new ArrayList<Trends>();
		JedisPool jp = RedisMine.getPool();
		Jedis jedis = jp.getResource();
		String ukey = RedisCommon.REDIS_TRENDS_USER_STORE_PREFFIX + userId;//组装redis获取用户动态的key
		long us = jedis.llen(ukey);
		if(us < RedisCommon.REDIS_TRENDS_USER_STORE_COUNT || count <= RedisCommon.REDIS_TRENDS_USER_STORE_COUNT){
			List<String> sList = jedis.lrange(ukey, 0, -1);
			long minus = (us > count ? count : us);
			for(int i=0;i<minus;i++){
				tList.add(new Gson().fromJson(sList.get(i), Trends.class));
			}
			RedisMine.returnResource(jp, jedis);
		}
		else if(count > RedisCommon.REDIS_TRENDS_USER_STORE_COUNT){
			TrendsQueryBuilder tqb = new TrendsQueryBuilder();
			tqb.setSize(count);
			tList = trendsDao.selectTrendsByQuery(tqb);
		}
		return tList;
	}
}
