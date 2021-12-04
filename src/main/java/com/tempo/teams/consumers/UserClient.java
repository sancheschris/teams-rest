package com.tempo.teams.consumers;

import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;

import java.util.List;

public interface UserClient {

    List<ResponseUsers> getUsers();

    ResponseUser getUserById(String id);
}
