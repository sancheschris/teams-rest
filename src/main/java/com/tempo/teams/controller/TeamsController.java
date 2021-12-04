package com.tempo.teams.controller;


import com.tempo.teams.service.TeamsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Teams Rest")
@RestController
@RequestMapping("/v1")
public class TeamsController {

    @Autowired
    private TeamsServiceImpl teamsServiceImpl;

    @ApiOperation(value = "GetTest")
    @GetMapping
    public ResponseEntity<Object> test() {
        return teamsServiceImpl.retornaUsers();
    }

    @ApiOperation(value = "GetTest")
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> test(@PathVariable("id") String id) {
        return teamsServiceImpl.retornaUser(id);
    }

}
