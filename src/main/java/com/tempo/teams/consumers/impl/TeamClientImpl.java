package com.tempo.teams.consumers.impl;

import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;
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
public class TeamClientImpl implements TeamClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.get.teams}")
    private String url;

    @Override
    public List<ResponseTeams> getTeams() {
        try {
            var resourceUri = URI.create(url);
            var headers = RestClientUtils.buildHttpHeaders();

            return restTemplate.exchange(resourceUri, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<ResponseTeams>>(){}).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage(), null);
        }
    }

    @Override
    public ResponseTeam getTeamById(String id) {
        try {
            var resourceUri = URI.create(url + id);
            var headers = RestClientUtils.buildHttpHeaders();

            return restTemplate.exchange(resourceUri, HttpMethod.GET, new HttpEntity<>(headers), ResponseTeam.class).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage(), null);
        }
    }


}
