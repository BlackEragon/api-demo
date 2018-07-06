package com.hpe.msp.apidemo.util;

import com.alibaba.fastjson.JSON;
import com.hpe.msp.apidemo.domain.ErrorRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: BlackEragon
 * Email:  chen.p.peng@gmail.com
 * Date:   2018-03-14 上午11:33
 */
@Slf4j
public final class ResponseUtil {

    public static void bizResponse(HttpServletResponse response, String data) {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(data);
        } catch (IOException e) {
            //TODO log.warn();
        }
    }

    public static void errorResponse(HttpServletResponse response, ErrorRespVO errorRespVO) {

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(JSON.toJSONString(errorRespVO));
        } catch (IOException e) {
            //TODO log.warn();
        }
    }

    public static void errorResponse2(HttpServletResponse response, ErrorRespVO errorRespVO) {

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(JSON.toJSONString(errorRespVO));
        } catch (IOException e) {
            //TODO log.warn();
        }
    }

    public static boolean parasCheckPass(HttpServletResponse response, BindingResult result) {
        if (result.hasErrors()) {
            errorResponse(response, new ErrorRespVO("01", "参数校验失败", ResponseUtil.getErrors(result)));
            return false;
        } else {
            return true;
        }
    }

    public static Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }

}
