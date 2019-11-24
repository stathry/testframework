package com.github.testframework.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit执行顺序测试
 * @see <a href="https://www.ibm.com/developerworks/cn/education/java/j-junit4/index.html">深入探索 JUnit 4</a>
 * @author dongdaiming(董代明)
 * @date 2019-06-13
 */
@Slf4j
public class JunitExecutionOrderTest {

    @Test
    public void testMethod1() {
        log.info("run testMethod1");
    }

    @Test
    public void testMethod2() {
        log.info("run testMethod2");
    }

    @BeforeClass
    public static void beforeClassMethod() {
        log.info("run beforeClassMethod");
    }

    @Before
    public void beforeMethod() {
        log.info("run beforeMethod");
    }

    @After
    public void afterMethod() {
        log.info("run afterMethod");
    }

    @AfterClass
    public static void afterClassMethod() {
        log.info("run afterClassMethod");
    }
}
