package com.tempo.teams.consumers.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.internal.LinkedTreeMap;
import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import com.tempo.teams.utils.RestClientUtils;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
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

            JSONObject response = new JSONObject(
                    restTemplate.exchange(resourceUri, HttpMethod.GET, RestClientUtils.buildHttpEntity(), String.class)
            );
            return this.parserResponseUsers(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), null);
        }
    }

    @Override
    public ResponseUser getUserById(String id) {
        try {
            var resourceUri = URI.create(url + id);

            return restTemplate.exchange(resourceUri,
                    HttpMethod.GET,RestClientUtils.buildHttpEntity(), ResponseUser.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), null);
        }
    }

    private List<ResponseUsers> parserResponseUsers(JSONObject jsonObject) {
        List<LinkedTreeMap> stringList = new Gson().fromJson(jsonObject.getString("body"), ArrayList.class);

        List<ResponseUsers> usersList = new ArrayList<>();

        stringList.forEach(e -> usersList.add(new ResponseUsers(e.get("id").toString(), e.get("displayName").toString())));

        return usersList;
    }
}
