package com.longfor.daenerys3.demo.web.controller.error;

import com.longfor.daenerys3.core.exception.LFBizException;
import com.longfor.daenerys3.core.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dan.shan
 * @since 2018-03-07 20:39
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse badRequestHandler(HttpServletRequest request, Exception e) {
        log.warn(e.getMessage());
        return new BaseResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage(), null);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public BaseResponse forbiddenHandler(HttpServletRequest request, Exception e) {
        log.warn(e.getMessage());
        return new BaseResponse(String.valueOf(HttpStatus.FORBIDDEN.value()), e.getMessage(), null);
    }

    @ExceptionHandler(value = LFBizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse LFBizExceptionHandler(HttpServletRequest request, Exception e) {
        log.warn(e.getMessage());
        return new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse internalServerErrorHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return new BaseResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
    }

}
