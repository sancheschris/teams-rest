package com.tempo.teams.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateConfigTest {

    @InjectMocks
    private RestTemplateConfig restTemplateConfig;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateBuilder builder;


    @Test
    public void RestTemplateSuccess() {
        when(builder.build()).thenReturn(restTemplate);

        RestTemplate restTemplate = restTemplateConfig.restTemplate(builder);

        assertNotNull(restTemplate);
    }
}