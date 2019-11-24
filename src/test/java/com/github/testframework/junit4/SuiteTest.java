package com.github.testframework.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 使用org.junit.runners.Suite可以将多个测试类进行分类/打包测试
 * Created by dongdaiming on 2018-12-26 17:54
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ParameterizedTest.class,
        SpringBootParameterizedTest.class})
public class SuiteTest {
}
