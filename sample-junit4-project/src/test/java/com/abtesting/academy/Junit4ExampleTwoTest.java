package com.abtesting.academy;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



@RunWith(Parameterized.class)
public class Junit4ExampleTwoTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 4 }, { 10 }, { 11}, { 26 }, { 34 },
				{ 89 }, { 120 } });
	}

	int number;

	public Junit4ExampleTwoTest(int number) {
		this.number = number;
	}

	@Test
	public void evenNumberTest() {
		Assert.assertTrue((number % 2) == 0);
	}
}
