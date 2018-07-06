package com.hpe.msp.apidemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: BlackEragon
 * @email:  chen.p.peng@gmail.com
 * @date:   2018-07-06 11:44
 */
@SpringBootApplication
public class ApiDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiDemoApplication.class).web(true).run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setReadTimeout(60000);
        requestFactory.setConnectTimeout(5000);

        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }
}
