package com.tempo.teams.service;

import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.dto.UserDto;
import com.tempo.teams.presenter.UserResponse;
import com.tempo.teams.presenter.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl {

    @Autowired
    private UserClient userClient;

    public ResponseEntity<Object> retornaUsers() {

        List<UsersResponse> users = userClient.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<Object> retornaUser(String id) {

        UserResponse user = userClient.findOneUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
