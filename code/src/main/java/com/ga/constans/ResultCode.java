/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.constans;

/**
 * @description 结果返回状态码
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see     
 * @since   girladvice1.0
 */
public class ResultCode {

	/**通常操作成功*/
	public final static int SUCCESS = 1;

	/**操作失败*/
	public final static int FAILED = 0;

	/**其他操作*/
	public final static int OHTER = 2;
	
	/**异常错误码---start*/
	
	/**测试异常*/
	//所有的异常都需要在此操作
	public final static int MSG_TEST_EXCEPTION = 10000;
	/**异常错误码---end*/
}
