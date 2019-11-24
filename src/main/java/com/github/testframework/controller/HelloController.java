package com.github.testframework.controller;

import com.github.testframework.dto.HelloRequestDTO;
import com.github.testframework.dto.HelloResponseDTO;
import com.github.testframework.service.HelloService1;
import com.github.testframework.service.HelloService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-31
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService1 helloService1;
    @Autowired
    private HelloService2 helloService2;

    private static final int DEFAULT_HIDE_START_INDEX = 1;
    private static final int DEFAULT_HIDE_REPLACE_LENGTH = 2;

    @GetMapping("/hello1/{userName}")
    public HelloResponseDTO hello1(@PathVariable String userName) {
        String hello = helloService1.hello(userName, DEFAULT_HIDE_START_INDEX, DEFAULT_HIDE_REPLACE_LENGTH);
        return new HelloResponseDTO(userName, hello);
    }

    @GetMapping("/hello21/{userName}")
    public HelloResponseDTO hello21(@PathVariable String userName) {
        String hello = helloService2.hello(userName, DEFAULT_HIDE_START_INDEX, DEFAULT_HIDE_REPLACE_LENGTH);
        return new HelloResponseDTO(userName, hello);
    }

    @PostMapping("/hello22")
    public HelloResponseDTO hello22(@RequestBody HelloRequestDTO helloRequest) {
        String hello = helloService2.hello(helloRequest.getUserName(), DEFAULT_HIDE_START_INDEX, DEFAULT_HIDE_REPLACE_LENGTH);
        return new HelloResponseDTO(helloRequest.getUserName(), hello);
    }

}
