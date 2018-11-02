package com.github.chriswhite199.feignmockmvctest.config;

import com.github.chriswhite199.feignmockmvctest.RestTemplateFeignClient;
import feign.Client;
import org.springframework.context.annotation.Bean;

public class TestRestTemplateFeignConfiguration {
    @Bean
    Client feignClient() {
        return new RestTemplateFeignClient();
    }
}
