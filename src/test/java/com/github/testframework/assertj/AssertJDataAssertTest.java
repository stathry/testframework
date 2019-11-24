package com.github.testframework.assertj;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * AssertJ常见数据类型断言测试
 * <p>
 * JUnit的Assert功能较少，推荐使用AssertJ
 * @see <a href="https://github.com/joel-costigliola/assertj-examples">assertj-examples</a>
 * </p>
 * @author dongdaiming(董代明)
 * @date 2019-06-13 13:53
 */
public class AssertJDataAssertTest {

    @Test
    public void testStringAssert() {
        String str = "AssertJ";
        String c = "Z";
        Assertions.assertThat(str).isNotBlank();
        Assertions.assertThat(str).startsWith("Ass");
        Assertions.assertThat(str).contains("ss");
        Assertions.assertThat(str).matches("A\\w+J");

        Assertions.assertThat(c).isIn("X", "Z");
        Assertions.assertThat(c).isEqualToIgnoringCase("z");
    }

    @Test
    public void testNumberAssert() {
        int n = 1024;

        Assertions.assertThat(n).isEqualTo(1024);
        Assertions.assertThat(n).isGreaterThanOrEqualTo(1024);
        Assertions.assertThat(n).isLessThan(1025);
        Assertions.assertThat(n).isPositive();
        Assertions.assertThat(n).isBetween(1023, 1025);
        Assertions.assertThat(n).isIn(1024, 1000);
    }

    @Test
    public void testLocalDatetimeAssert() {
        LocalDateTime t1 = LocalDateTime.of(2019, 6, 13, 18, 0);
        LocalDateTime t2 = LocalDateTime.of(2019, 6, 13, 20, 0);
        LocalDateTime t3 = LocalDateTime.of(2019, 6, 13, 22, 0);

        Assertions.assertThat(t1).isBefore(t2);
        Assertions.assertThat(t1).isEqualToIgnoringHours(t2);
        Assertions.assertThat(t2).isBeforeOrEqualTo("2019-06-13T22:00:00");
        Assertions.assertThat(t2).isBetween(t1, t3);
    }

    @Test
    public void testCollectionAssert() {
        Collection<Integer> collection = Arrays.asList(6, 8, 10);

        Assertions.assertThat(collection).isNotEmpty().hasSize(3);
        Assertions.assertThat(collection).size().isBetween(2, 4);
        Assertions.assertThat(collection).first().isEqualTo(6);
        Assertions.assertThat(collection).last().isEqualTo(10);
        Assertions.assertThat(collection).containsAnyOf(8, 88);
        Assertions.assertThat(collection).contains(6, 10);
        Assertions.assertThat(collection).containsOnly(6, 8, 10);
    }

    @Test
    public void testMapAssert() {
        Map<Integer, String> map = ImmutableMap.of(6, "v6", 8, "v8", 10, "v10");

        Assertions.assertThat(map).isNotEmpty().hasSize(3);
        Assertions.assertThat(map).size().isGreaterThan(2);
        Assertions.assertThat(map).containsKeys(6, 8);
        Assertions.assertThat(map).containsOnlyKeys(10, 8 , 6);
        Assertions.assertThat(map).containsValues("v8", "v6");
        Assertions.assertThat(map).containsOnly(Assertions.entry(6, "v6"), Assertions.entry(8, "v8"), Assertions.entry(10, "v10"));

    }

}
