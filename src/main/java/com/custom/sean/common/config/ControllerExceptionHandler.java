package com.custom.sean.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.custom.sean.common.exception.CheckedException;

/**
 * @author sean
 * 2017/11/2.
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> nullPointerException(Exception ex) {
        //自定义异常信息并添加到请求域中
        log.error("NullPointerException: {}", ex.getMessage());
        Map<String, Object> result = new HashMap<>(2);
        result.put("statusCode", "1");
        result.put("statusMessage", "空指针异常啦...");
        return result;
    }

    @ExceptionHandler(CheckedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.MULTIPLE_CHOICES)
    public Map<String, Object> checkedException(CheckedException ex) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", "300");
        result.put("message", ex.getMessage());
        return result;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> accessDeniedException(AccessDeniedException ex) {
        Map<String, Object> result = new HashMap(2);
        result.put("code", HttpStatus.FORBIDDEN);
        result.put("message", ex.getMessage());
        return result;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> sqlException(SQLException ex) {
        Map<String, Object> result = new HashMap(2);
        result.put("code", HttpStatus.NOT_ACCEPTABLE);
        result.put("message", ex.getMessage());
        return result;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> entityNotFoundException(EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage());
        Map<String, Object> result = new HashMap(2);
        result.put("code", HttpStatus.NOT_ACCEPTABLE);
        result.put("message", ex.getMessage());
        return result;
    }


}
