package com.github.chriswhite199.feignmockmvctest;

import com.github.chriswhite199.feignmockmvctest.config.MockMvcFeignConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableFeignClients(defaultConfiguration = MockMvcFeignConfiguration.class)
public class MockMvcFeignClientTest extends BaseTest {
}
