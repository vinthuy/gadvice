/*
 * Copyright (c) ga 2013
 * <a href="www.ga.test">我们会一起努力</a>
 * Create Date : 2013-9-17
 */

package com.ga.web.model;

/**
 * @description 消息返回类
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-9-17
 * @param <T>
 * @see
 * @since girladvice1.0
 */
public class ResultModel<T> {
	/** 填充数据 */
	private T data;
	/** 提示消息 */
	private int msgCode;
	/** 状态码 */
	private int code;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	public int getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(int msgCode) {
		this.msgCode = msgCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
