package com.cswhite.feignmockmvctest.config;

import com.cswhite.feignmockmvctest.MockMvcFeignClient;
import feign.Client;
import org.springframework.context.annotation.Bean;

public class MockMvcFeignConfiguration {
    @Bean
    Client feignClient() {
        return new MockMvcFeignClient();
    }
}
