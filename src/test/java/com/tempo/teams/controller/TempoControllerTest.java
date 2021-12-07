//package com.tempo.teams.controller;
//
//import com.tempo.teams.entity.Team;
//import com.tempo.teams.entity.User;
//import com.tempo.teams.entity.UserTeam;
//import com.tempo.teams.presenter.ResponseUserTeam;
//import com.tempo.teams.service.impl.TempoServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import javax.servlet.http.HttpServletRequest;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(TempoController.class)
//public class TempoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private HttpServletRequest httpRequest;
//
//    @InjectMocks
//    private TempoController tempoController;
//
//    @Mock
//    private TempoServiceImpl tempoServiceImpl;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(tempoController).build();
//    }
//
//    @Test
//    public void getRoleByUserAndTeamIdSuccess() throws Exception {
//
//        UserTeam userTeam = new UserTeam();
//
//        Team team = new Team();
//        team.setId("0165537a-d71d-4b01-88f3-14f01c2615ad");
//        team.setName("TIME");
//        team.setTeamLeadId("1205537a-d71d-4b01-88f3-14f01c2615ad");
//
//        User user = new User();
//
//        user.setId("1205537a-d71d-4b01-88f3-14f01c2615ad");
//        user.setFirstName("Chris");
//
//        userTeam.setTeam(team);
//        userTeam.setUser(user);
//        userTeam.setRoles("Developer");
//        userTeam.setId("3593137b-d71d-4b01-99r3-14f01c2615ad");
//
//
//        when(httpRequest.getRequestURL()).thenReturn(new StringBuffer("v1/role/{teamId}/{userId}"));
//        when(tempoServiceImpl.getRoleByUserIdAndTeamId("0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad")).thenReturn(ResponseEntity.ok(userTeam));
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("v1/role/0165537a-d71d-4b01-88f3-14f01c2615ad/1205537a-d71d-4b01-88f3-14f01c2615ad")
//                                .accept(MediaType.APPLICATION_JSON)
//
//                ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//
//        verify(tempoServiceImpl, times(1))
//                .getRoleByUserIdAndTeamId("0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad");
//
//
//    }
//
//    @Test
//    public void getRoleSuccess() throws Exception {
//
//        UserTeam userTeam = new UserTeam();
//
//        Team team = new Team();
//        team.setId("0165537a-d71d-4b01-88f3-14f01c2615ad");
//        team.setName("TIME");
//        team.setTeamLeadId("1205537a-d71d-4b01-88f3-14f01c2615ad");
//
//        User user = new User();
//
//        user.setId("1205537a-d71d-4b01-88f3-14f01c2615ad");
//        user.setFirstName("Chris");
//
//        userTeam.setTeam(team);
//        userTeam.setUser(user);
//        userTeam.setRoles("Developer");
//        userTeam.setId("3593137b-d71d-4b01-99r3-14f01c2615ad");
//
//        ResponseUserTeam responseUserTeam = new ResponseUserTeam();
//        responseUserTeam.setRoles("Manager");
//        responseUserTeam.setId("3029ce81-1822-4065-a9a2-3d0d1eb5d3ef");
//
//        when(httpRequest.getRequestURL()).thenReturn(new StringBuffer("v1/role/{teamId}/{userId}"));
//        when(tempoServiceImpl.createNewRole("3029ce81-1822-4065-a9a2-3d0d1eb5d3ef", "Manager", "0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad"))
//                .thenReturn(ResponseEntity.ok(responseUserTeam));
//
//        this.mockMvc.perform(get("v1/id/Manager")
//                        .param("teamId", "0165537a-d71d-4b01-88f3-14f01c2615ad")
//                        .param("userId", "1205537a-d71d-4b01-88f3-14f01c2615ad"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//
//        verify(tempoServiceImpl, times(1))
//                .createNewRole("3029ce81-1822-4065-a9a2-3d0d1eb5d3ef", "Manager", "0165537a-d71d-4b01-88f3-14f01c2615ad", "1205537a-d71d-4b01-88f3-14f01c2615ad");
//
//    }
//}