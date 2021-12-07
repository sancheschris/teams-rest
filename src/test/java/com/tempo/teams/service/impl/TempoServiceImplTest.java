package com.tempo.teams.service.impl;

import com.tempo.teams.entity.Team;
import com.tempo.teams.entity.User;
import com.tempo.teams.entity.UserTeam;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.repository.TeamRepository;
import com.tempo.teams.repository.UserRepository;
import com.tempo.teams.repository.UserTeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TempoServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserTeamRepository userTeamRepository;

    @InjectMocks
    private TempoServiceImpl tempoServiceImpl;

    @Test
    public void GetRoleByUserIdAndTeamOK() {

        List<UserTeam> userTeams = new ArrayList<>();

        when(userTeamRepository.getRoleByTeamIdAndUserId("7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190")).thenReturn(userTeams);

        ResponseEntity<Object> roleByUserIdAndTeamId = tempoServiceImpl.getRoleByUserIdAndTeamId("7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");

        assertEquals(HttpStatus.OK, roleByUserIdAndTeamId.getStatusCode());
    }

    @Test
    public void GetRoleByUserIdAndTeamException() {
        when(userTeamRepository.getRoleByTeamIdAndUserId("7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190"))
                .thenThrow(new BadRequestException("teste"));

        try {
            tempoServiceImpl.getRoleByUserIdAndTeamId("7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void createNewRoleSuccess() {

        List<UserTeam> roleByTeamIdAndUserId = new ArrayList<>();
        UserTeam userTeam = new UserTeam();
        userTeam.setId("caaadd01-e073-4a3f-bd72-bebea0d9e329");
        userTeam.setRoles("Manager");

        roleByTeamIdAndUserId.add(userTeam);

        doNothing().when(userTeamRepository).insertNewRole(anyString(), anyString(), anyString(), anyString());
        when(userTeamRepository.getRoleByTeamIdAndUserId(anyString(), anyString())).thenReturn(roleByTeamIdAndUserId);

        var response = tempoServiceImpl.createNewRole(anyString(), anyString(), anyString(), anyString());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userTeam.getId(), roleByTeamIdAndUserId.get(0).getId());
    }

    @Test
    public void createNewRoleInsertException() {

        doThrow(new InternalServerErrorException("teste", null)).when(userTeamRepository).insertNewRole(anyString(), anyString(), anyString(), anyString());

        try {
            tempoServiceImpl.createNewRole(anyString(), anyString(), anyString(), anyString());
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void createNewRolegetRoleException() {

        when(userTeamRepository.getRoleByTeamIdAndUserId(anyString(), anyString())).thenThrow(new InternalServerErrorException("teste", null));

        try {
            tempoServiceImpl.createNewRole(anyString(), anyString(), anyString(), anyString());
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void getMembershipsByRoleSuccess() {

        UserTeam userTeam = new UserTeam();

        UserTeam userTeam2 = new UserTeam();

        when(userTeamRepository.findTopByRoles(anyString())).thenReturn(userTeam);

        var membershipsByRole = tempoServiceImpl.getMembershipsByRole(anyString());

        assertEquals(HttpStatus.OK, membershipsByRole.getStatusCode());
        assertEquals(userTeam, userTeam2);
        assertEquals(userTeam2.hashCode(), userTeam.hashCode());
    }

    @Test
    public void getMembershipsByRoleException() {

        when(userTeamRepository.findTopByRoles(anyString())).thenThrow(new BadRequestException("teste"));

        try {
            tempoServiceImpl.getMembershipsByRole(anyString());
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

    @Test
    public void assignRoleSuccess() {

        var team = new Team();
        team.setId("7676a4bf-adfe-415c-941b-1739af07039b");

        var t2 = new Team();
        t2.setId("7676a4bf-adfe-415c-941b-1739af07039b");

        var user = new User();
        var u2 = new User();

        when(teamRepository.findById("7676a4bf-adfe-415c-941b-1739af07039b")).thenReturn(Optional.of(team));
        when(userRepository.findById("b12fa35a-9c4c-4bf9-8f32-27cf03a1f190")).thenReturn(Optional.of(user));

        var objectResponseEntity = tempoServiceImpl.assignRoleMember("Manager", "7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");

        assertEquals(HttpStatus.NO_CONTENT, objectResponseEntity.getStatusCode());
        assertEquals( -2069554100, team.hashCode());
        assertEquals(t2, team);
        assertEquals(u2, user);
    }

    @Test
    public void assignRoleExceptionFindTeamById() {

        when(teamRepository.findById("7676a4bf-adfe-415c-941b-1739af07039b")).thenThrow(new BadRequestException("teste"));

       try {
           tempoServiceImpl.assignRoleMember("Manager", "7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");
           fail();
       } catch (Exception e) {
           assertEquals("teste", e.getMessage());
       }
    }

    @Test
    public void assignRoleExceptionFindUserById() {

        when(userRepository.findById("b12fa35a-9c4c-4bf9-8f32-27cf03a1f190")).thenThrow(new BadRequestException("teste"));

        try {
            tempoServiceImpl.assignRoleMember("Manager", "7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");
            fail();
        } catch (Exception e) {
            assertEquals("teste", e.getMessage());
        }
    }

//    @Test
//    public void assignRoleExceptionUpdateRole() {
//        try {
//            tempoServiceImpl.assignRoleMember("Manager", "7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");
//            fail();
//        } catch (Exception e) {
//            assertNotNull(e.getMessage());
//        }
//    }

}