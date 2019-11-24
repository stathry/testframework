package com.github.testframework.testng;

import com.github.testframework.util.StringEncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TestNG批量参数化测试
 *
 * @author dongdaiming(董代明)
 * @date 2019-06-13
 */
@Slf4j
public class TestNGParameterizedTest {

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
    public void testReplaceInIndex(String inputString, int startIndex, int replaceLength, String expectedResult) {
        String actualResult = StringEncodeUtils.replaceInIndex(inputString, startIndex, replaceLength);
        log.info("inputString: '{}', startIndex: {}, replaceLength: {},    expectedResult: '{}', actualResult: '{}'",
                inputString, startIndex, replaceLength, expectedResult, actualResult);

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

}
