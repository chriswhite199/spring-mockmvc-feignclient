package com.cswhite.feignmockmvctest;

import feign.Client;
import feign.Request;
import feign.Response;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cswhite.feignmockmvctest.FeignRequestUtils.convertRequestHeaders;
import static com.cswhite.feignmockmvctest.FeignRequestUtils.convertRequestMethod;

/**
 * Feign {@link Client} implementation that uses {@link TestRestTemplate} instance
 */
public class RestTemplateFeignClient implements Client {
    @Autowired
    private TestRestTemplate restTemplate;

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        // extract root url for test server instance
        URL rootUrl = new URL(restTemplate.getRootUri());

        // re-write URL for TestRestTemplate's root url (scheme, host, port)
        String url = UriComponentsBuilder.fromUriString(request.url())
                .scheme(rootUrl.getProtocol())
                .host(rootUrl.getHost())
                .port(rootUrl.getPort())
                .toUriString();

        HttpMethod method = convertRequestMethod(request);
        byte[] body = request.body();
        HttpHeaders httpHeaders = convertRequestHeaders(request);

        HttpEntity<byte[]> reqEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<byte[]> resEntity = this.restTemplate.exchange(url, method, reqEntity, byte[].class);

        return this.convertResponse(request, resEntity);
    }

    @SneakyThrows
    private Response convertResponse(Request request, ResponseEntity<byte[]> resEntity) {
        return Response.builder()
                .request(request)
                .status(resEntity.getStatusCodeValue())
                .body(resEntity.getBody())
                .headers(resEntity.getHeaders().entrySet().stream().collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .build();
    }
}
