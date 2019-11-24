package com.github.testframework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * HelloResponseDTO
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-31
 */
@Data
@AllArgsConstructor
public class HelloResponseDTO {

    private String userName;
    private String helloUserName;
}
