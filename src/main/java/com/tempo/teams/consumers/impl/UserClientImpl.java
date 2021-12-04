package com.tempo.teams.consumers.impl;

import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.dto.UserDto;
import com.tempo.teams.presenter.UserResponse;
import com.tempo.teams.presenter.UsersResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@Log4j2
public class UserClientImpl implements UserClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.get.users}")
    private String url;

    @Override
    public List<UsersResponse> getUsers() {

        try {
            URI resourceUri = URI.create(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Accept", "*/*");

            return restTemplate.exchange(resourceUri,
                    HttpMethod.GET,new HttpEntity<>(headers), new ParameterizedTypeReference<List<UsersResponse>>(){}).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public UserResponse findOneUser(String id) {
        try {
            URI resourceUri = URI.create(url + id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Accept", "*/*");

            return restTemplate.exchange(resourceUri,
                    HttpMethod.GET,new HttpEntity<>(headers), UserResponse.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
