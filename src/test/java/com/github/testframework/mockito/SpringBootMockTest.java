package com.github.testframework.mockito;

import com.alibaba.fastjson.JSON;
import com.github.testframework.dto.HelloRequestDTO;
import com.github.testframework.dto.HelloResponseDTO;
import com.github.testframework.service.HelloService1;
import com.github.testframework.service.HelloService2;
import com.github.testframework.service.HelloService3;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * SpringBootMockTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@Slf4j
public class SpringBootMockTest {

    @Autowired
    private HelloService1 helloService1;

    @MockBean
    private HelloService2 helloService2;

    @SpyBean
    private HelloService3 helloService3;

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testMockHelloController() throws Exception {
        String inputUserName = "abcd";
        String res1 = mvc.perform(MockMvcRequestBuilders.get("/hello1/{userName}", inputUserName)).andReturn().getResponse().getContentAsString();
        System.out.println("testMockHelloController_get, res:\n" + res1);
        HelloResponseDTO resJSON1 = JSON.parseObject(res1, HelloResponseDTO.class);
        Assert.assertEquals("a**d", resJSON1.getHelloUserName());

        Mockito.when(helloService2.hello(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn("unknown");

        String res21 = mvc.perform(MockMvcRequestBuilders.get("/hello21/{userName}",  inputUserName)).andReturn().getResponse().getContentAsString();
        System.out.println("testMockHelloController_mock_get, res:\n" + res21);
        HelloResponseDTO resJSON21 = JSON.parseObject(res21, HelloResponseDTO.class);
        Assert.assertEquals("unknown", resJSON21.getHelloUserName());

        HelloRequestDTO request = new HelloRequestDTO("abcd");
        String res22 = mvc.perform(MockMvcRequestBuilders.post("/hello22").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(request)))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testMockHelloController_mock_post, res:\n" + res22);
        HelloResponseDTO resJSON22 = JSON.parseObject(res22, HelloResponseDTO.class);
        Assert.assertEquals("unknown", resJSON22.getHelloUserName());

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
