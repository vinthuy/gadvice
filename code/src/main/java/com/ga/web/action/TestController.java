package com.ga.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ga.constans.ResultCode;
import com.ga.service.TestService;
import com.ga.web.model.ResultModel;

/**
 * @description test类
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-9-17
 * @see
 * @since girladvice1.0
 */
@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
public class TestController {

	private static final Logger logger = Logger.getLogger(TestController.class);
	@Autowired
	private TestService testService;

	@RequestMapping("/index.do")
	public String index() {
		System.out.println("123");
		return "home";
	}

	/**
	 * @description 增加test
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @since girladvice1.0.0
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/testadd.do")
	@ResponseBody
	public ResultModel testadd(@RequestParam String name) {
		ResultModel result = new ResultModel();
		try {
			testService.insert(name);
			result.setCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info(e.getMessage());
			result.setMsgCode(ResultCode.MSG_TEST_EXCEPTION);
			result.setCode(ResultCode.FAILED);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/test/{name}")
	@ResponseBody
	public ResultModel test(@PathVariable("name") String name) {
		ResultModel result = new ResultModel();
		try {
			logger.info("restful-->" + name);
			result.setCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED);
		}
		return result;
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
