package com.github.chriswhite199.feignmockmvctest.config;

import com.github.chriswhite199.feignmockmvctest.MockMvcFeignClient;
import feign.Client;
import org.springframework.context.annotation.Bean;

public class MockMvcFeignConfiguration {
    @Bean
    Client feignClient() {
        return new MockMvcFeignClient();
    }
}
