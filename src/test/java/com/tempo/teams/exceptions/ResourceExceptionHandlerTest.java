package com.tempo.teams.exceptions;

import com.tempo.teams.presenter.ResponseError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler resourceExceptionHandler;

    @Test
    public void InternalServerError_Test() {
        //cenario
        String errorMessage = "INTERNAL SERVER ERROR EXCEPTION";
        Exception e = new Exception(errorMessage);

        //ação
        ResponseEntity<ResponseError> response = resourceExceptionHandler.handleInternalServer(e, null);

        // verificação
        assertEquals("500", response.getBody().getErros().get(0).getCode());
        assertEquals(errorMessage, response.getBody().getErros().get(0).getDetail());
        assertEquals(1, response.getBody().getErros().size());
        assertEquals("INTERNAL SERVER ERROR EXCEPTION", response.getBody().getErros().get(0).getTitle());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void BadRequestException_Test() {
        //cenario
        String errorMessage = "BAD REQUEST EXCEPTION";
        InternalServerErrorException e = new InternalServerErrorException(errorMessage, null);

        //ação
        ResponseEntity<ResponseError> response = resourceExceptionHandler.handleBadRequest(e, null);

        // verificação
        assertEquals("400", response.getBody().getErros().get(0).getCode());
        assertEquals(errorMessage, response.getBody().getErros().get(0).getDetail());
        assertEquals(1, response.getBody().getErros().size());
        assertEquals("BAD REQUEST EXCEPTION", response.getBody().getErros().get(0).getTitle());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}