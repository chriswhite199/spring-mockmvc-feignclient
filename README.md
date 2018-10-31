# Spring Mock Feign Clients

[![Build Status](https://travis-ci.org/chriswhite199/spring-mockmvc-feignclient.svg?branch=master)](https://travis-ci.org/chriswhite199/spring-mockmvc-feignclient)

Simple library for testing Feign Clients using MockMvc or TestRestTemplate instances.

Useful for when you want to test your Spring REST Controllers and associated Feign Client for compatibility.

## Usage

See the `src/test/java` folder for a fully fledged example, but in short:

1. Include this library as a test dependency
1. Add Feign global configuration (to use `MockMvc` or `TestRestTemplate` based upon your preference)
    * MockMvc should execute quicker as TestRestTemplate requires a full server stack

### MockMvc Usage
```java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableFeignClients(defaultConfiguration = MockMvcFeignConfiguration.class)
public class MockMvcFeignClientTest {
    // ...
}

public class MockMvcFeignConfiguration {
    @Bean
    Client feignClient() {
        return new MockMvcFeignClient();
    }
}
```

### TestRestTemplate Usage
```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableFeignClients(defaultConfiguration = TestRestTemplateFeignConfiguration.class)
public class RestTemplateFeignClientTest {
    // ...
}

public class TestRestTemplateFeignConfiguration {
    @Bean
    Client feignClient() {
        return new RestTemplateFeignClient();
    }
}
```
