package com.hpe.msp.apidemo.controller;

import com.alibaba.fastjson.JSON;
import com.hpe.msp.apidemo.domain.TeleRespVO;
import com.hpe.msp.apidemo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
public class SalesOrderController {

    @PostMapping(value = "/v1/sales-orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postBizQualification(@Valid @RequestBody TeleRespVO teleRespVO,
                                     BindingResult bindingResult,
                                     HttpServletResponse response) {
        log.error("接收请求报文，body:{}", JSON.toJSONString(teleRespVO));

        if (ResponseUtil.parasCheckPass(response, bindingResult)) {
            String data = "{\n" +
                    "    \"bizCode\": \"1000\",\n" +
                    "    \"bizDesc\": \"接收报文成功\"\n" +
                    "}";
            ResponseUtil.bizResponse(response, data);
        }
    }

    @GetMapping(value = "/v1/check")
    public void check(HttpServletResponse response) {
        String data = "{\n" +
                "    \"bizCode\": \"1000\",\n" +
                "    \"bizDesc\": \"检测成功\"\n" +
                "}";
        ResponseUtil.bizResponse(response, data);

    }
}
