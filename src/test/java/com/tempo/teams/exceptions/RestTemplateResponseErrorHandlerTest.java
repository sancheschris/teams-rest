package com.tempo.teams.exceptions;

import com.google.gson.Gson;
import com.tempo.teams.presenter.Errors;
import com.tempo.teams.presenter.ResponseError;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateResponseErrorHandlerTest {


    @Mock
    private ClientHttpResponse response;

    @Mock
    private InputStream body;

    @InjectMocks
    private RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;


    @Test
    public void hasError_Catch_Internal_Test() throws IOException {
        // cenario
        String msg = "test";

        when(body.readAllBytes()).thenReturn(msg.getBytes());
        when(response.getBody()).thenReturn(body);
        when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(response.getRawStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());

        // ação
        try {
            restTemplateResponseErrorHandler.handleError(response);
            fail();
        } catch (InternalServerErrorException e) {
            assertEquals("Não foi possível converter o erro em: " + ResponseError.class, msg , e.getMessage());
        }

    }

    @Test
    public void hasError_Test_CLIENT_ERROR() throws IOException {
        //cenario
        when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        //acção
        boolean resp = restTemplateResponseErrorHandler.hasError(response);

        assertTrue(resp);
    }

    @Test
    public void hasError_Test_SERVER_ERROR() throws IOException {
        //cenario
        when(response.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        //acção
        boolean resp = restTemplateResponseErrorHandler.hasError(response);

        assertTrue(resp);
    }
}