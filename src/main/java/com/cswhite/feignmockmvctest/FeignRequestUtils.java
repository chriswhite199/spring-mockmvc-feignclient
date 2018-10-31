package com.cswhite.feignmockmvctest;

import feign.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;

/**
 * Utility methods for converting between a Feign {@link Request} and Spring Http objects
 */
public class FeignRequestUtils {
    /**
     * Convert Feign {@link Request} headers collection to Spring {@link HttpHeaders} object
     */
    public static HttpHeaders convertRequestHeaders(final Request request) {
        final HttpHeaders springHeaders = new HttpHeaders();
        request.headers().forEach((header, vals) -> springHeaders.put(header, new ArrayList<String>(vals)));
        return springHeaders;
    }

    /**
     * Convert Feign {@link Request} request method to Spring {@link HttpMethod} object
     */
    public static HttpMethod convertRequestMethod(final Request request) {
        return HttpMethod.valueOf(request.method());
    }
}
