package com.abtesting.academy;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.smartbear.zephyrscale.junit.annotation.TestCase;


public class Junit4ExampleOneTest {

	@Test
    @TestCase(name = "Test that always passes")
	public void passingTest() {
		
		
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	@TestCase(key = "ABTA-1")
	public void failingTest() {
		Assert.assertTrue(Boolean.FALSE);
	}
	
	
	@Ignore
	public void skippingTest() {
		Assert.assertTrue(Boolean.FALSE);
	}
	
	
}
