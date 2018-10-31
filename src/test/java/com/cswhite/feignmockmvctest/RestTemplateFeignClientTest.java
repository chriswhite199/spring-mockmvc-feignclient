package com.cswhite.feignmockmvctest;

import com.cswhite.feignmockmvctest.config.TestRestTemplateFeignConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableFeignClients(defaultConfiguration = TestRestTemplateFeignConfiguration.class)
public class RestTemplateFeignClientTest extends BaseTest {
}
