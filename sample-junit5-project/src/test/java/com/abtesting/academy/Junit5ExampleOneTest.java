package com.abtesting.academy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.abtesting.academy.annotations.TestCase;


class Junit5ExampleOneTest {

	@TestCase(name = "Test that always Green", key = "ABCD-143")
	void passingTest() {
		Assertions.assertTrue(Boolean.TRUE);
	}
	
	@Test
	void failingTest() {
		methodThatThrowsException();
		Assertions.assertTrue(Boolean.FALSE);
	}
	
	
	@Disabled(value = "Just like that disabled")
	void skippingTest() {
		Assertions.assertTrue(Boolean.FALSE);
	}
	
	
	private void methodThatThrowsException() {
		throw new RuntimeException("Runtime Exception");
	}
	
	
}
