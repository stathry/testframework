package com.github.testframework.junit4;

import com.github.testframework.service.HelloService1;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.Collection;

/**
 * Junit批量参数化测试
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-22
 * @see <a href="https://blog.csdn.net/benweizhu/article/details/42365811">Parameterized与SpringRunner</a>
 */
@Slf4j
@RunWith(Parameterized.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class SpringBootParameterizedTest {

    @Autowired
    private HelloService1 helloService;

    private final TestContextManager testContextManager;

    private String inputString;
    private int startIndex;
    private int replaceLength;
    private String expectedResult;

    public SpringBootParameterizedTest(String inputString, int startIndex, int replaceLength, String expectedResult) {
        this.inputString = inputString;
        this.startIndex = startIndex;
        this.replaceLength = replaceLength;
        this.expectedResult = expectedResult;
        this.testContextManager = new TestContextManager(getClass());
    }

    @Before
    public void prepareTestInstance() throws Throwable {
        this.testContextManager.prepareTestInstance(this);
    }

    // 传入参数/预期结果的顺序与构造函数中的顺序应一致
    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {null, 0, 0, ""},
                        {"", 0, 0, ""},
                        {" ", 0, 0, ""},
                        {"", 0, 2, ""},
                        {"a", 0, 0, "a"},
                        {"a", 0, 2, "**"},
                        {"a", 1, 2, "a**"},
                        {"ab", 1, 2, "a**"},
                        {"abc", 1, 2, "a**"},
                        {"abcd", 1, 2, "a**d"},
                        {"a", 2, 2, "a***"},
                        {"a", 3, 2, "a****"},
                        {"12345678901", 3, 4, "123****8901"},
                        {"", -1, 0, ""},
                        {"", 0, -1, ""},
                }
        );
    }

    @Test
    public void testReplaceInIndex() {
        String actualResult = helloService.hello(inputString, startIndex, replaceLength);
        log.info("inputString: '{}', startIndex: {}, replaceLength: {},    expectedResult: '{}', actualResult: '{}'",
                inputString, startIndex, replaceLength, expectedResult, actualResult);

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

}
