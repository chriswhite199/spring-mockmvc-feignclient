package com.cswhite.feignmockmvctest.config;

import com.cswhite.feignmockmvctest.RestTemplateFeignClient;
import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TestRestTemplateFeignConfiguration {
    @Bean
    Client feignClient() {
        return new RestTemplateFeignClient();
    }
}
