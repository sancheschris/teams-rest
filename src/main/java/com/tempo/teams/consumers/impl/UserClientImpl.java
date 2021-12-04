package com.tempo.teams.consumers.impl;

import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import com.tempo.teams.utils.RestClientUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    public List<ResponseUsers> getUsers() {

        try {
            var resourceUri = URI.create(url);
            var headers = RestClientUtils.buildHttpHeaders();

            return restTemplate.exchange(resourceUri,
                    HttpMethod.GET,new HttpEntity<>(headers), new ParameterizedTypeReference<List<ResponseUsers>>(){}).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseUser getUserById(String id) {
        try {
            var resourceUri = URI.create(url + id);
            var headers = RestClientUtils.buildHttpHeaders();

            return restTemplate.exchange(resourceUri,
                    HttpMethod.GET,new HttpEntity<>(headers), ResponseUser.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
