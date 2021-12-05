//package com.tempo.teams.consumers.impl;
//
//import com.tempo.teams.config.RestTemplateConfig;
//import com.tempo.teams.consumers.UserClient;
//import com.tempo.teams.exceptions.InternalServerErrorException;
//import com.tempo.teams.presenter.ResponseUsers;
//import org.hamcrest.Matchers;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserClientImplTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    private RestTemplateConfig restTemplateConfig;
//
//    @InjectMocks
//    private UserClientImpl userClientImpl;
//
//    private String url;
//
//    private HttpHeaders headers;
//
//    private URI resourceUri;
//
//    @Before
//    public void setUp() {
//        ReflectionTestUtils.setField(userClientImpl, "url", "https://www.teste.com.br");
////        ReflectionTestUtils.setField(restTemplateConfig, "restTemplate", restTemplate);
//    }
//
//    private void cenarioStartTest() {
//        this.resourceUri = URI.create("https://www.teste.com.br");
//        this.headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
////    @Test
////    public void getUsersFail() {
////
////        //cenario
////        cenarioStartTest();
////
////        ResponseUsers responseUsers = new ResponseUsers("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e", "randyFunk");
////        List<ResponseUsers> responseUsersList = new ArrayList<>();
////        responseUsersList.add(responseUsers);
////
////        //ação
////        when(restTemplate.exchange(resourceUri,
////                HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<ResponseUsers>>() {
////                }).getBody())
////                .thenThrow(new InternalServerErrorException("teste", null));
////
////        try {
////            userClientImpl.getUsers();
////            fail();
////        } catch (Exception e) {
////            assertEquals("teste", e.getMessage());
////        }
////    }
//
////    @Test
////    public void getUsersSuccess() {
////
////        //cenario
////        cenarioStartTest();
////
////        ResponseUsers responseUsers = new ResponseUsers("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e", "randyFunk");
////        List<ResponseUsers> responseUsersList = new ArrayList<>();
////        responseUsersList.add(responseUsers);
////
////        //ação
////        ParameterizedTypeReference<List<ResponseUsers>> responseType = new ParameterizedTypeReference<>() {
////        };
////
////
////
////        when(restTemplate.exchange(resourceUri,
////                HttpMethod.GET, new HttpEntity<>(headers), responseType).getBody())
////                .thenReturn(responseUsersList);
////
////
//////        when(restTemplate.exchange(
//////                ArgumentMatchers.anyString(),
//////                ArgumentMatchers.any(HttpMethod.class),
//////                ArgumentMatchers.any(),
//////                ArgumentMatchers.<ParameterizedTypeReference<List<Object>>>any())
//////        ).thenReturn(new ResponseEntity<>(responseUsers, HttpStatus.OK));
////
////        userClientImpl.getUsers();
////
//////        assertEquals();
////
////
////    }
//}