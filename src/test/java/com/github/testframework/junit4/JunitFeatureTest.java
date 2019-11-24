package com.github.testframework.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * JunitFeatureTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-06-13
 */
@Slf4j
@RunWith(JunitOrderedRunner.class)
@SpringBootTest
public class JunitFeatureTest {

    @Test(expected = ArithmeticException.class)
    public void testExpectedException() {
        int n = 1 / 0;
    }

    @Ignore
    @Test
    public void testIgnoreTest() {
        log.info("testIgnoreTest");
    }

    @Test(timeout = 2000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(1000);
        log.info("testTimeout");
    }

    // FixMethodOrder注解可以指定被测试的方法的排序规则, 但不支持自定义方法顺序.
    // 可通过重写junit默认的runner-org.junit.runners.JUnit4的父类的BlockJUnit4ClassRunner.computeTestMethods来实现自定义规则
    @Test
    @MethodOrder(2)
    public void testMethodOrder2() {
        log.info("testMethodOrder2");
    }

    @Test
    @MethodOrder(1)
    public void testMethodOrder1() {
        log.info("testMethodOrder1");
    }

}
