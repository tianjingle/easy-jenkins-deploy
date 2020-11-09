package com.scaffold.easy.jenkins.client.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 12:28
 * @Eamil: 2695062879@qq.com
 */
@Configuration
public class MyConfig {

    @Bean
    public RestTemplate newRestTemplate(){
        return new RestTemplate();
    }
}
