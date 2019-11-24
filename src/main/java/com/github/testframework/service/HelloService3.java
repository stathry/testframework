package com.github.testframework.service;

import com.github.testframework.util.StringEncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * HelloService
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-22
 */
@Service
@Slf4j
public class HelloService3 {

    public String hello(String userName, int startIndex, int replaceLength) {
        String result = StringEncodeUtils.replaceInIndex(userName, startIndex, replaceLength);
        log.info("input userName: '{}', startIndex: {}, replaceLength: {}, output: '{}'.", userName, startIndex, replaceLength, result);
        return result;
    }

    public String helloToDo() {
        throw new UnsupportedOperationException();
    }
}
