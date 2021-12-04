package com.tempo.teams.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class RestClientUtils {

    private RestClientUtils(){

    }

    public static HttpHeaders buildHttpHeaders() {
        var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
