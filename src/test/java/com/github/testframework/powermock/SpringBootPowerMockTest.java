package com.github.testframework.powermock;

import com.github.testframework.service.HelloService1;
import com.github.testframework.service.HelloService2;
import com.github.testframework.service.HelloService3;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBootMockTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-25
 */
@PowerMockIgnore({"javax.management.", "com.sun.org.apache.xerces.",
        "javax.xml.", "org.xml.", "org.w3c.dom.",
        "com.sun.org.apache.xalan.", "javax.activation.*"})
@PowerMockRunnerDelegate(SpringRunner.class)
@RunWith(PowerMockRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootPowerMockTest {

    @Autowired
    private HelloService1 helloService1;

    @MockBean
    private HelloService2 helloService2;

    @SpyBean
    private HelloService3 helloService3;

    @Test
    public void test() {
    }

    @Test
    public void testHello() {
        String inputString = "12345678901", expectedResult = "123****8901";
        int startIndex = 3, replaceLength = 4;
        String actualResult = helloService1.hello(inputString, startIndex, replaceLength);
        log.info("inputString: '{}', startIndex: {}, replaceLength: {},    expectedResult: '{}', actualResult: '{}'",
                inputString, startIndex, replaceLength, expectedResult, actualResult);

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testMockHello() {
        Mockito.when(helloService2.helloToDo()).thenReturn("hello");
        Assertions.assertThat(helloService2.helloToDo()).isEqualTo("hello");
    }

    @Test
    public void testPartMock() {
        String inputString = "12345678901", expectedResult = "123****8901";
        int startIndex = 3, replaceLength = 4;
        String actualResult = helloService3.hello(inputString, startIndex, replaceLength);
        log.info("inputString: '{}', startIndex: {}, replaceLength: {},    expectedResult: '{}', actualResult: '{}'",
                inputString, startIndex, replaceLength, expectedResult, actualResult);

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);

        Mockito.when(helloService2.helloToDo()).thenReturn("hello");
        Assertions.assertThat(helloService2.helloToDo()).isEqualTo("hello");
    }

}
