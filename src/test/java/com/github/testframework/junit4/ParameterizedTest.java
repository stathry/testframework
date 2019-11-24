package com.github.testframework.junit4;

import com.github.testframework.util.StringEncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Junit批量参数化测试
 *
 * @author dongdaiming(董代明)
 * @date 2019-06-13
 * @see <a href="https://www.ibm.com/developerworks/cn/education/java/j-junit4/index.html">深入探索JUnit4</a>
 */
@Slf4j
@RunWith(Parameterized.class)
public class ParameterizedTest {

    private String inputString;
    private int startIndex;
    private int replaceLength;
    private String expectedResult;

    public ParameterizedTest(String inputString, int startIndex, int replaceLength, String expectedResult) {
        this.inputString = inputString;
        this.startIndex = startIndex;
        this.replaceLength = replaceLength;
        this.expectedResult = expectedResult;
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
        String actualResult = StringEncodeUtils.replaceInIndex(inputString, startIndex, replaceLength);
        log.info("inputString: '{}', startIndex: {}, replaceLength: {},    expectedResult: '{}', actualResult: '{}'",
                inputString, startIndex, replaceLength, expectedResult, actualResult);

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

}
