package com.ga.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ga.constans.ResultCode;
import com.ga.domain.Trends;
import com.ga.service.TrendsService;
import com.ga.web.model.ResultModel;

/**
 * @description 动态信息控制类
 * @author  <a href="mailto:charlie166@163.com">李阳</a>
 * @version 1.0, 2013-11-18
 * @see     
 * @since   ga1.0
 */
@Controller
public class TrendsController {

	@Autowired
	private TrendsService trendsService;
	
	
	public void setTrendsService(TrendsService trendsService) {
		this.trendsService = trendsService;
	}
	
	/**
	 * @description 跳转到动态页面
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @return 
	 * @since ga1.0.0
	 */
	@RequestMapping(value="/goTrends.do")
	public String goTrendsPage(){
		return "trends";
	}
	
	@RequestMapping(value="/trends/postPage.do")
	public String goTrendsTest(){
		return "trends/trends_add";
	}
	
	/**
	 * @description 动态发布，其中userId为必须，确保保存位置
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @param trends
	 * @return 
	 * @since ga1.0.0
	 */
	@RequestMapping(value="/trends/post.do", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel<Object> postOneTrends(Trends trends){
		return trendsService.insertOneTrends(trends);
	}
	
	/**
	 * @description 查询某个用户的的最新发表的动态列表
	 * @author <a href="mailto:charlie166@163.com">李阳</a>
	 * @return 
	 * @since ga1.0.0
	 */
	@RequestMapping(value="/trends/user/search.do", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel<Object> searchUserTrends(
			@RequestParam(defaultValue="0") int count,
			@RequestParam(defaultValue="0") int userId
			){
		if(count <= 0)
			count = 10;
		ResultModel<Object> result = new ResultModel<Object>();
		if(userId <= 0){
			result.setCode(ResultCode.FAILED);
			result.setMessage("用户id必须大于0");
		} else {
			List<Trends> tList = trendsService.findRecentTrends(count, userId);
			result.setCode(ResultCode.SUCCESS);
			result.setData(tList);
		}
		return result;
	}
}
