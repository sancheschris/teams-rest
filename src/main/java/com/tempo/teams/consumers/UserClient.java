package com.tempo.teams.consumers;

import com.tempo.teams.dto.UserDto;
import com.tempo.teams.model.User;
import com.tempo.teams.presenter.UserResponse;
import com.tempo.teams.presenter.UsersResponse;

import java.util.List;

public interface UserClient {

    List<UsersResponse> getUsers();

    UserResponse findOneUser(String id);
}
