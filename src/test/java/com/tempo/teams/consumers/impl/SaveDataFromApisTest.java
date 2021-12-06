package com.tempo.teams.consumers.impl;

import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.dto.UserDto;
import com.tempo.teams.entity.Team;
import com.tempo.teams.entity.User;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;
import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import com.tempo.teams.repository.TeamRepository;
import com.tempo.teams.repository.UserRepository;
import com.tempo.teams.repository.UserTeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveDataFromApisTest {

    @Mock
    private UserClient userClient;

    @Mock
    private TeamClient teamClient;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserTeamRepository userTeamRepository;

    @InjectMocks
    private SaveDataFromApis saveDataFromApis;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void FailsWhenGetUserClient() {

        //cenario
        when(userClient.getUsers()).thenThrow(new InternalServerErrorException("teste", null));

        try {
            saveDataFromApis.saveUserAndTeamFromAPIToDatabase();
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void FailsWhenGetTeamsClient() {

        //cenario
        List<ResponseUsers> allUsers = new ArrayList<>();

        when(userClient.getUsers()).thenReturn(allUsers);
        when(teamClient.getTeams()).thenThrow(new InternalServerErrorException("teste", null));

        try {
            saveDataFromApis.saveUserAndTeamFromAPIToDatabase();
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void GetUsersById() {

        //cenario

        ResponseUsers responseUsers = new ResponseUsers("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c", "gianniWehner");

        List<ResponseUsers> allUsers = new ArrayList<>();
        allUsers.add(responseUsers);

        ResponseTeams responseTeams = new ResponseTeams("7676a4bf-adfe-415c-941b-1739af07039b", "Ordinary Coral Lynx");
        List<ResponseTeams> allTeams = new ArrayList<>();
        allTeams.add(responseTeams);

        List<User> listUsers = new ArrayList<>();

        ResponseUser reponseUser = new ResponseUser("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c", "Gianni", "Wehner", "gianniWehner", "https://cdn.fakercloud.com/avatars/rude_128.jpg", "Brakusstad");

        when(userClient.getUsers()).thenReturn(allUsers);
        when(teamClient.getTeams()).thenReturn(allTeams);
        when(userClient.getUserById("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c")).thenReturn(reponseUser);

        ResponseUser userById = userClient.getUserById(reponseUser.getId());
        User user = new User(userById.getId(), userById.getFirstName(), userById.getLastName(), userById.getDisplayName(), userById.getAvatarUrl(), userById.getLocation());
        listUsers.add(user);

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e"));
        userDtos.add(new UserDto("54383a18-425c-4f50-9424-1c4c27e776dd"));
        userDtos.add(new UserDto("e0dba3dc-313d-4648-bd9c-4ddc8b189e84"));
        userDtos.add(new UserDto("b047d3f4-3469-47ce-a03f-1637a6de036b"));
        userDtos.add(new UserDto("ee91a519-fefa-48a7-bdf7-672bde38aef9"));
        userDtos.add(new UserDto("197c2b23-1218-44d0-b6b8-d757ba004515"));
        userDtos.add(new UserDto("e947058e-2d5f-47d9-925b-27bcab14c38e"));

        List<UserDto> teamMemberIds = new ArrayList<>();
        teamMemberIds.addAll(userDtos);

        ResponseTeam responseTeam = new ResponseTeam("7676a4bf-adfe-415c-941b-1739af07039b", "Ordinary Coral Lynx", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190",teamMemberIds);

        when(teamClient.getTeamById("7676a4bf-adfe-415c-941b-1739af07039b")).thenReturn(responseTeam);

        Team team = new Team(responseTeam.getId(), responseTeam.getName(), responseTeam.getTeamLeadId());


        saveDataFromApis.saveUserAndTeamFromAPIToDatabase();

        assertEquals(user, listUsers.get(0));
        assertEquals(user.hashCode(), -603073772);
        assertNotNull(reponseUser.toString());
        verify(userClient, atLeastOnce()).getUsers();
        verify(userClient, atLeastOnce()).getUserById(anyString());
        verify(teamClient, atLeastOnce()).getTeams();
        verify(teamClient, atLeastOnce()).getTeamById(anyString());
    }
}