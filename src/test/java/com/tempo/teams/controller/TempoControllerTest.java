//package com.tempo.teams.controller;
//
//import com.tempo.teams.service.impl.TempoServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.Assert.assertNotNull;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TempoControllerTest {
//
//    @Mock
//    private TempoServiceImpl tempoServiceImpl;
//
//    @InjectMocks
//    private TempoController tempoController;
//
//    @Test
//    public void getRoleByUserAndTeamIdSuccess() {
//
//        ResponseEntity<Object> roleByUserAndTeamId = tempoController.getRoleByUserAndTeamId("7676a4bf-adfe-415c-941b-1739af07039b", "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190");
//
//        assertNotNull(roleByUserAndTeamId);
//
//    }
//}