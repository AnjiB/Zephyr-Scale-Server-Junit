package com.abtesting.academy.extn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import com.abtesting.academy.annotations.TestCase;
import com.abtesting.academy.dto.CustomTestCase;
import com.abtesting.academy.dto.ResultContainer;
import com.abtesting.academy.dto.TestExecution;
import com.abtesting.academy.dto.TestExecution.TestExecutionBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZephyrServerJunit5Extension implements TestWatcher, AfterAllCallback {

  private static final String FAILED = "Failed: ";

  private static final String DISABLED = "Disabled: ";

  private static final String ABORTED = "Aborted: ";

  private static final String PASSED = "Passed";

  private static final String ZEPHYRSCALE_RESULT_JSON_FILE_PATH = System.getProperty("reportFilePath", "./zephyrscale_result.json");

  private static List<TestExecution> executions = Collections.synchronizedList(new ArrayList<TestExecution>());

  @Override
  public void testSuccessful(ExtensionContext context) {

    TestExecutionBuilder testExecutionBuilder = getTestExecutionBuilder(context);
    updateResult(testExecutionBuilder, PASSED);
    executions.add(testExecutionBuilder.build());
  }

  @Override
  public void testAborted(ExtensionContext context, Throwable cause) {

    TestExecutionBuilder testExecutionBuilder = getTestExecutionBuilder(context);
    updateResult(testExecutionBuilder, ABORTED + context.getExecutionException().get().getMessage());
    executions.add(testExecutionBuilder.build());
  }

  @Override
  public void testDisabled(ExtensionContext context, Optional<String> reason) {

    TestExecutionBuilder testExecutionBuilder = getTestExecutionBuilder(context);
    updateResult(testExecutionBuilder, DISABLED + reason);
    executions.add(testExecutionBuilder.build());

  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    TestExecutionBuilder testExecutionBuilder = getTestExecutionBuilder(context);
    updateResult(testExecutionBuilder, FAILED + context.getExecutionException().get().getMessage());
    executions.add(testExecutionBuilder.build());
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ResultContainer container = ResultContainer.builder().executions(executions).build();
    writeResultsToJsonFile(container);
  }

  private TestExecutionBuilder getTestExecutionBuilder(ExtensionContext context) {

    TestExecutionBuilder testExecutionBuilder = TestExecution.builder();

    testExecutionBuilder.source(
        context.getRequiredTestClass().getCanonicalName() + "." + context.getRequiredTestMethod().getName());

    if (context.getElement().isPresent() && context.getElement().get().isAnnotationPresent(TestCase.class)) {

      TestCase testCase = context.getElement().get().getAnnotation(TestCase.class);

      testExecutionBuilder.testCase(CustomTestCase.builder().name(testCase.name()).key(testCase.key()).build());

    }

    return testExecutionBuilder;
  }

  private TestExecutionBuilder updateResult(TestExecutionBuilder builder, String result) {
    return builder.result(result);

  }

  private void writeResultsToJsonFile(ResultContainer container) {

    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ZEPHYRSCALE_RESULT_JSON_FILE_PATH), container);
    } catch (IOException e) {
      log.error(e.getLocalizedMessage());
    }
  }

}
