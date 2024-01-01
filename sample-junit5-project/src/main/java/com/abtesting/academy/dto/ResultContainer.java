package com.abtesting.academy.dto;

import java.util.List;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Builder
@Data
public class ResultContainer {
	
	@Default
	private Integer version = 1;
	
	private List<TestExecution> executions;
	
}
