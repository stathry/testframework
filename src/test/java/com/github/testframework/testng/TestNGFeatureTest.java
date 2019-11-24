package com.github.testframework.testng;


import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * TestNGFeatureTest
 * Created by dongdaiming on 2018-12-27 09:13
 * @see <a href="http://www.devtalking.com/articles/testng-powermock/">testng</a>
 */
@Slf4j
public class TestNGFeatureTest {

    @Test(invocationCount = 3)
    public void testInvocationCount() {
        logRunInfo();
    }

    @Test(expectedExceptions = {NullPointerException.class, NumberFormatException.class})
    public void testExpectedException() {
//        Integer i = null;
//        int n = i;

//        int n2 = Integer.parseInt("a");

//        int n3 = 1 / 0;
    }

    @Test(enabled = false)
    public void testIgnoreTest() {
        logRunInfo();
    }

    @Test(timeOut = 5_000)
    public void testNotTimeout() throws InterruptedException {
        Thread.sleep(3_000);
        logRunInfo();
    }

    @Test(timeOut = 1_000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(3_000);
    }

    @Test(threadPoolSize = 2, invocationCount = 4)
    public void testByThreadPool() {
        logRunInfo();
    }

    @Test
    public void testFail() {
        Assert.fail();
    }

    @Test
    public void testSuccess() {
        logRunInfo();
    }

    @Test(dependsOnMethods = {"testFail"})
    public void testDependOnFail() {
        logRunInfo();
    }

    @Test(dependsOnMethods = {"testSuccess"})
    public void testDependOnSuccess() {
        logRunInfo();
    }

    @Test(priority = 2)
    public void testPriority2() {
        logRunInfo();
    }

    @Test(priority = 1)
    public void testPriority1() {
        logRunInfo();
    }

    private void logRunInfo() {
        // 获取当前方法信息: Thread.currentThread().getStackTrace()[1]
        // 获取最近的调用方法信息: Thread.currentThread().getStackTrace()[2]
        log.info("[{}] - finished testCase: '{}' at {}", Thread.currentThread().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), LocalDateTime.now());
        System.out.println();
    }
}
