package com.tempo.teams.controller;


import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import com.tempo.teams.service.impl.TeamsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Teams Rest")
@RestController
@RequestMapping("/v1")
public class TeamsController {

    @Autowired
    private TeamsServiceImpl teamsServiceImpl;

    @ApiOperation(value = "GetTest")
    @GetMapping
    public List<ResponseUsers> getAllUsers() {
        return teamsServiceImpl.getAllUsers();
    }

    @ApiOperation(value = "GetTest")
    @GetMapping("/user/{id}")
    public ResponseUser test(@PathVariable("id") String id) {
        return teamsServiceImpl.getUserById(id);
    }

    @ApiOperation(value = "getTeamById")
    @GetMapping("/teams/{id}")
    public ResponseEntity<Object> getTeamById(@PathVariable("id") String id) {
        return teamsServiceImpl.getTeamById(id);
    }

    @ApiOperation(value = "getTeams")
    @GetMapping("/teams")
    public ResponseEntity<Object> getTeams() {
        return teamsServiceImpl.getAllTeams();
    }
}
