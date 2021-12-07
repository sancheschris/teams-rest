package com.tempo.teams.consumers.impl;

import com.google.gson.Gson;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.presenter.ResponseTeams;
import com.tempo.teams.presenter.ResponseUsers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TeamClientImpl teamClientImpl;

    private String url;

    private HttpHeaders headers;

    private URI resourceUri;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(teamClientImpl, "url", "https://www.teste.com.br");
    }

    private void cenarioStartTest() {
        this.resourceUri = URI.create("https://www.teste.com.br");
        this.headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private void cenarioStartTestById() {
        this.resourceUri = URI.create("https://www.teste.com.br/" + "371d2ee8-cdf4-48cf-9ddb-04798b79ad9e");
        this.headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void getTeamsFail() {

        //cenario
        cenarioStartTest();

        //ação
        when(restTemplate.exchange(resourceUri, HttpMethod.GET, new HttpEntity<>(headers), String.class))
                .thenThrow(new InternalServerErrorException("teste", null));

        try {
            teamClientImpl.getTeams();
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void getTeamsSuccess() {

        //cenario
        cenarioStartTest();

        ResponseTeams responseTeams = new ResponseTeams("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e", "randyFunk");
        List<ResponseTeams> responseTeamsList = new ArrayList<>();
        responseTeamsList.add(responseTeams);

        //ação
        String bodyString = new Gson().toJson(responseTeamsList);

        when(restTemplate.exchange(resourceUri, HttpMethod.GET, new HttpEntity<>(headers), String.class))
                .thenReturn(ResponseEntity.ok(bodyString));

        var expected = teamClientImpl.getTeams();

        assertEquals("randyFunk", expected.get(0).getName());
    }

}