package com.hpe.msp.apidemo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * Author: BlackEragon
 * Email:  chen.p.peng@gmail.com
 * Date:   2018-03-12 下午4:47
 */
@NoArgsConstructor
@Data
public class ErrorRespVO {
    private String errorCode;
    private String errorMsg;
    private String timestamp;
    private Map<String, String> errors;

    public ErrorRespVO(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.timestamp = ZonedDateTime.now(ZoneOffset.UTC).toString();
    }

    public ErrorRespVO(String errorCode, String errorMsg, Map<String, String> errors) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.timestamp = ZonedDateTime.now(ZoneOffset.UTC).toString();
        this.errors = errors;
    }

}
