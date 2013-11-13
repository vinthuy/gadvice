/*
 * Copyright (c) Joygame 2013
 * <a href="www.cmge.com">中国手游</a>
 * Create Date : 2013-9-4
 */
package com.ga.support;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * @description 线程管理
 * @author  <a href="mailto:ruiyong.hu@daw.so">胡瑞永</a>
 * @version 1.0, 2013-9-4
 * @see     
 * @since   socialservice1.0
 */
public class ThreadManager {
	
	private static final Logger logger = Logger.getLogger(ThreadManager.class);
	
	/**线程数量*/
	private int threadSize;
	
	private ExecutorService threadService = null;
	
	/**
	 * @description 初始化线程池
	 * @author <a href="mailto:ruiyong.hu@daw.so">胡瑞永</a> 
	 * @since socialservice1.0.0
	 */
	public void init() {
		
		if(this.getThreadSize()>0) {
			threadService = Executors.newFixedThreadPool(threadSize);
			logger.info("Init thread pool [size=" + threadSize + "]");
		}
		
	     //缓冲线程执行器，产生一个大小可变的线程池。
	     //当线程池的线程多于执行任务所需要的线程的时候，
	     //对空闲线程（即60s没有任务执行）进行回收；
	     //当执行任务的线程数不足的时候，自动拓展线程数量。因此线程数量是JVM
	     //可创建线程的最大数目。
		else 
			threadService = Executors.newCachedThreadPool();
	}
	
	public void executeThread(Runnable thread){
		if(threadService!=null)
			threadService.execute(thread);
	}
	public ExecutorService getThreadService() {
		return threadService;
	}
	public void setThreadService(ExecutorService threadService) {
		this.threadService = threadService;
	}

	public int getThreadSize() {
		return threadSize;
	}

	public void setThreadSize(int threadSize) {
		this.threadSize = threadSize;
	}
	/** 
	 * @description 关闭线程池
	 * @author <a href="mailto:ruiyong.hu@daw.so">胡瑞永</a> 
	 * @since socialservice1.0.0
	 */
	public void distory(){
		threadService.shutdown();
	}
	
}
