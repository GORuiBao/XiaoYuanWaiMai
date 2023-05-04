package com.YR.reggie.common;

/**
 * ClassName: CustomException
 * Description: 自定义业务异常
 * date: 2023/4/20 0020 17:28
 *
 * @author YR
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
