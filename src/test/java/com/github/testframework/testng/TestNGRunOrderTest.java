package com.github.testframework.testng;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * TestNGRunOrderTest
 * Created by dongdaiming on 2018-12-26 17:00
 */
@Slf4j
public class TestNGRunOrderTest {
    @BeforeTest
    public void testBeforeTest() {
        logInfo();
    }

    @BeforeClass
    public void testBeforeClass() {
        logInfo();
    }

    @BeforeMethod
    public void testBeforeMethod() {
        logInfo();
    }

    @Test
    public void testMethod1() {
        logInfo();
    }

    @Test
    public void testMethod2() {
        logInfo();
    }

    @AfterMethod
    public void testAfterMethod() {
        logInfo();
    }

    @AfterClass
    public static void testAfterClass() {
        logInfo();
    }

    @AfterTest
    public static void testAfterTest() {
        logInfo();
    }

    private static void logInfo() {
        // 获取当前方法信息: Thread.currentThread().getStackTrace()[1]
        // 获取最近的调用方法信息: Thread.currentThread().getStackTrace()[2]
        log.info("[{}] - finished method: '{}' at {}", Thread.currentThread().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), LocalDateTime.now());
    }
}
