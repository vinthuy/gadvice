package com.ga.commons.test;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public abstract class SpringNoContextConfigTestCase extends AbstractJUnit4SpringContextTests {
		
	protected static Logger logger;

	public SpringNoContextConfigTestCase() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	protected void assertEquals(Object expected, Object actual) {
		Assert.assertEquals(expected, actual);
	}

	protected void assertEquals(String message, Object expected, Object actual) {
		Assert.assertEquals(message, expected, actual);
	}

	protected void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	protected void assertTrue(String message, boolean condition) {
		Assert.assertTrue(message, condition);
	}

	protected void assertFalse(boolean condition) {
		Assert.assertFalse(condition);
	}

	protected void assertFalse(String message, boolean condition) {
		Assert.assertFalse(message, condition);
	}

	protected void assertNull(Object object) {
		Assert.assertNull(object);
	}

	protected void assertNull(String message, Object object) {
		Assert.assertNull(message, object);
	}

	protected void assertNotNull(Object object) {
		Assert.assertNotNull(object);
	}

	protected void assertNotNull(String message, Object object) {
		Assert.assertNotNull(message, object);
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException localInterruptedException) {
		}
	}
}