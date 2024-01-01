package com.abtesting.academy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Junit5ExampleTwoTest {

	@ParameterizedTest(name = "verify the number {0} is even or not")
	@ValueSource(ints = {4, 10, 11, 26, 34, 89, 120})
	void evenNumberTest(int num) {
		Assertions.assertTrue((num % 2) == 0);
	}
}
