package com.tempo.teams.service;

import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;

import java.util.List;

public interface UsersService {

    List<ResponseUsers> getAllUsers();
    ResponseUser getUserById(String id);
}
