package com.ga.commons.test;

import org.springframework.test.context.ContextConfiguration;

/**
 * @description 依赖Spring容器资源的单元测试类
 * @author  vinthuy@qq.com 胡瑞永
 * @version 1.0, 2013-8-15
 * @see     
 * @since   social-commons1.0
 */
@ContextConfiguration(locations={"/applicationContext.xml"})
public abstract class SpringContextTestCase extends SpringNoContextConfigTestCase {

}