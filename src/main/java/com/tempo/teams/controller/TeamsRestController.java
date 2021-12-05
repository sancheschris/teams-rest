package com.tempo.teams.controller;

import com.tempo.teams.consumers.impl.SaveDataFromApis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Get users and teams from API and save it on a database")
@RestController
@RequestMapping("/v1")
public class TeamsRestController {

    @Autowired
    private SaveDataFromApis saveDataFromApis;

    @ApiOperation(value = "Save the returned data into database.")
    @GetMapping("/data")
    public ResponseEntity<Object> getDataFromApiAndSaveIt() {
        saveDataFromApis.saveUserAndTeamFromAPIToDatabase();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
