package com.tempo.teams.controller;

import com.tempo.teams.entity.Team;
import com.tempo.teams.entity.User;
import com.tempo.teams.entity.UserTeam;
import com.tempo.teams.presenter.ResponseUserTeam;
import com.tempo.teams.service.TempoService;
import com.tempo.teams.service.impl.TempoServiceImpl;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TempoControllerTest {

    @InjectMocks
    private TempoController tempoController;

    @Mock
    private TempoServiceImpl tempoServiceImpl;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(this.tempoController);
    }

    @Test
    public void getRoleByUserAndTeamIdSuccess() {
        UserTeam userTeam = getUserTeam();

        when(tempoServiceImpl.getRoleByUserIdAndTeamId(anyString(), anyString())).thenReturn(ResponseEntity.ok(userTeam));

        var responseEntity = tempoController.getRoleByUserAndTeamId("0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tempoServiceImpl, times(1)).getRoleByUserIdAndTeamId(anyString(), anyString());
    }

    @Test
    public void createRoleSuccess() {

        UserTeam userTeam = getUserTeam();

        when(tempoServiceImpl.createNewRole(anyString(), anyString(), anyString(), anyString())).thenReturn(ResponseEntity.created(URI.create("/id/Developer")).body(userTeam));

        var responseEntity = tempoController.createRole("Developer", "a75504a8-7a04-4837-8f72-180c2b19a887", "0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad");

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(tempoServiceImpl, times(1)).createNewRole(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void getMembershipByRoleSuccess() {
        UserTeam userTeam = getUserTeam();

        when(tempoServiceImpl.getMembershipsByRole(anyString())).thenReturn(ResponseEntity.ok(userTeam));

        var responseEntity = tempoController.getMembershipByRole("Developer");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tempoServiceImpl, times(1)).getMembershipsByRole(anyString());

    }

    @Test
    public void assignRoleMemberSuccess() {

        when(tempoServiceImpl.assignRoleMember(anyString(), anyString(), anyString())).thenReturn(ResponseEntity.noContent().build());

        var responseEntity = tempoController.assignRoleMember("0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad", "Manager");

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(tempoServiceImpl, times(1)).assignRoleMember(anyString(), anyString(), anyString());

    }

    private UserTeam getUserTeam() {
        UserTeam userTeam = new UserTeam();

        Team team = new Team();
        team.setId("0165537a-d71d-4b01-88f3-14f01c2615ad");
        team.setName("TIME");
        team.setTeamLeadId("1205537a-d71d-4b01-88f3-14f01c2615ad");

        User user = new User();

        user.setId("1205537a-d71d-4b01-88f3-14f01c2615ad");
        user.setFirstName("Chris");

        userTeam.setTeam(team);
        userTeam.setUser(user);
        userTeam.setRoles("Developer");
        userTeam.setId("3593137b-d71d-4b01-99r3-14f01c2615ad");
        return userTeam;
    }
}