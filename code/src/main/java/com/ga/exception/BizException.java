/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-11-2
 */

package com.ga.exception;

/**
 * @description 业务异常类
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see     
 * @since   girladvice1.0
 */
public class BizException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**异常消息*/
	private String msg;
	
	public BizException(String ExceptionMSg){
		super(ExceptionMSg);
		this.msg = ExceptionMSg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
