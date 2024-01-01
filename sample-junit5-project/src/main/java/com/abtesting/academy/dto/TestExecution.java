package com.abtesting.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class TestExecution {

	private String source;

	private String result;
	
	@Default
	private CustomTestCase testCase = null;
}
