package com.cswhite.feignmockmvctest;

import feign.Client;
import feign.Request;
import feign.Response;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URLDecoder;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.cswhite.feignmockmvctest.FeignRequestUtils.convertRequestHeaders;
import static com.cswhite.feignmockmvctest.FeignRequestUtils.convertRequestMethod;

/**
 * Feign {@link Client} implementation that uses {@link MockMvc} instance
 */
public class MockMvcFeignClient implements Client {
    @Autowired
    private MockMvc mockMvc;

    @Override
    @SneakyThrows
    public Response execute(Request request, Request.Options options) {
        // MockMvc path to controller doesn't decode the + char to spaces
        String decodedUrl = URLDecoder.decode(request.url(), "UTF-8");

        HttpMethod method = convertRequestMethod(request);
        byte[] body = request.body();
        HttpHeaders httpHeaders = convertRequestHeaders(request);

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.request(method, decodedUrl)
                        .content(body)
                        .headers(httpHeaders))
                .andReturn();

        MockHttpServletResponse resp = mvcResult.getResponse();

        return this.convertResponse(request, resp);
    }

    private Response convertResponse(Request request, MockHttpServletResponse resp) {
        return Response.builder()
                .request(request)
                .status(resp.getStatus())
                .body(resp.getContentAsByteArray())
                .headers(resp.getHeaderNames().stream()
                        .collect(Collectors.toMap(Function.identity(), resp::getHeaders)))
                .build();
    }
}
