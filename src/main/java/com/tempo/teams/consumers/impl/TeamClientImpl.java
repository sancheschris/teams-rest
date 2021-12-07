package com.tempo.teams.consumers.impl;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;
import com.tempo.teams.presenter.ResponseUsers;
import com.tempo.teams.utils.RestClientUtils;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class TeamClientImpl implements TeamClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.get.teams}")
    private String url;

    @Override
    public List<ResponseTeams> getTeams() {
        try {
            var resourceUri = URI.create(url);

            JSONObject response = new JSONObject(
                    restTemplate.exchange(resourceUri, HttpMethod.GET, RestClientUtils.buildHttpEntity(), String.class)
            );

            return this.parserResponseTeams(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), null);
        }
    }

    @Override
    public ResponseTeam getTeamById(String id) {
        try {
            var resourceUri = URI.create(url + id);

            return restTemplate.exchange(resourceUri, HttpMethod.GET, RestClientUtils.buildHttpEntity(), ResponseTeam.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), null);
        }
    }

    private List<ResponseTeams> parserResponseTeams(JSONObject jsonObject) {
        List<LinkedTreeMap> stringList = new Gson().fromJson(jsonObject.getString("body"), ArrayList.class);

        List<ResponseTeams> responseTeams = new ArrayList<>();

        stringList.forEach(e -> responseTeams.add(new ResponseTeams(e.get("id").toString(), e.get("name").toString())));
        return responseTeams;
    }

}
