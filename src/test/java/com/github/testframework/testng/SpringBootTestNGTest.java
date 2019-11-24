package com.github.testframework.testng;

import com.github.testframework.service.HelloService1;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TestNG批量参数化测试
 *
 * @author dongdaiming(董代明)
 * @date 2019-06-13
 * @see <a href="https://github.com/spring-projects/spring-boot/tree/master/spring-boot-tests/spring-boot-smoke-tests/spring-boot-smoke-test-testng">spring-boot-smoke-test-testng</a>
 */
@SpringBootTest
@Slf4j
public class SpringBootTestNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HelloService1 helloService;

    @Test
    public void testSpringbootStarted() {
        Assertions.assertThat(helloService).isNotNull();
        log.info("testSpringbootStarted: {}", helloService.toString());
    }

    // 传入参数/预期结果的顺序与构造函数中的顺序应一致
    @DataProvider(name = "replaceInIndexData")
    public static Object[][] replaceInIndexData() {
        return new Object[][]{
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
        };
    }

    @Test(description = "用'*'替换掉字符串中指定索引和长度的字符", dataProvider = "replaceInIndexData")
    public void testHello(String inputString, int startIndex, int replaceLength, String expectedResult) {
        String actualResult = helloService.hello(inputString, startIndex, replaceLength);
        log.info("inputString: '{}', startIndex: {}, replaceLength: {},    expectedResult: '{}', actualResult: '{}'",
                inputString, startIndex, replaceLength, expectedResult, actualResult);

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

}
