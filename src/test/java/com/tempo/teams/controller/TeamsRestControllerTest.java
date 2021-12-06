package com.tempo.teams.controller;

import com.tempo.teams.consumers.impl.SaveDataFromApis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TeamsRestControllerTest {

    @Mock
    private SaveDataFromApis saveDataFromApis;

    @InjectMocks
    private TeamsRestController teamsRestController;

    @Test
    public void GetDataFromApiSuccess() {

        ResponseEntity<Object> dataFromApiAndSaveIt = teamsRestController.getDataFromApiAndSaveIt();

        assertEquals(HttpStatus.NO_CONTENT, dataFromApiAndSaveIt.getStatusCode());
    }
}