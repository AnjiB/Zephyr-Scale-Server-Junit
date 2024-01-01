# Problem Statement
Not able to use Zephyr Scale Server API to publish Junit5 test results to Zephyr Scale Server / DC.

### Background
- Zephyr JIRA plugin comes in two versions, namely Zephyr Scale Cloud & Zephyr Scale Data Center / Server
- Zephyr Scale Cloud is for JIRA Cloud
- Zephyr Scale Server / DataCenter is for on-premise JIRA
- In order to publish Junit results to Zephyr Scale Server, Zephyr provides [set of APIs](https://support.smartbear.com/zephyr-scale-server/docs/en/rest-api.html) which we can use to publish Junit results
- Problem with Zephyr Server APIs is we cannot push junit xml files. Instead, we need to publish the results in json format
- Refer the format of json file here: https://github.com/SmartBear/zephyr-scale-junit-integration?tab=readme-ov-file#zephyrscale_resultjson
- In order to genenate above json file after test execution, Zephyr provides a [library](https://github.com/SmartBear/zephyr-scale-junit-integration?tab=readme-ov-file#usage) which can be added as dependency in your test project

### Problem
- Problem with above library is, it cannot be used for Junit5 test project as it uses RunListener which does not work with Junit5
- Another problem is, genereated json file does not contain all test results of a Parameterized test. This is the bug I noticed while playing with this library


### Solution
- I was thinking about two solutions to solve above issue. 
  1. Develop a library which works for Junit5. Meaning, after test execution is completed, this library would generate the json file that is in the same format of Zephyr Server expects
  2. Develop a library that translates Junit5 xml report to Zephyr Server json format. We can also convert Allure results to the same format as well
- In this project I developed **Solution 1** which works for Junit5 seamlessly and also I fixed Parameterized test bug which exists in this [Zephyr Junit Library](https://github.com/SmartBear/zephyr-scale-junit-integration/tree/main)
