package com.tempo.teams.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigTest {

    @InjectMocks
    private SwaggerConfig swaggerConfig;

    @Test
    public void ShouldReturnDocket () {
        Docket docket = swaggerConfig.api();
        assertNotNull(docket);
    }
}