//package com.tempo.teams.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
//@PrepareForTest(DataSourceConfig.class)
//public class DataSourceConfigTest {
//
//    @InjectMocks
//    private DataSourceConfig dataSourceConfig;
//
//    @Test
//    public void getDataSourceArqrShouldReturnDataSource() throws Exception {
//        String expectedPassword = "1234";
//        String testString = "test";
//        Number testNumber = 1000;
//        //cria a instancia do objeto de conexao do banco
//        HikariConfig config = mock(HikariConfig.class);
//
//        HikariDataSource expectedDataSource = mock(HikariDataSource.class);
//
//        //mock quando for executado o new Hikari na classe dataSource
////        doReturn(config).when(HikariConfig.class);
////        whenNew(HikariConfig.class).withNoArguments().thenReturn(config);
////        doReturn(expectedDataSource).when(HikariDataSource.class);
////        whenNew(HikariDataSource.class).withArguments(config).thenReturn(expectedDataSource);
//        //mock para simular o retorno da senha descriptografada
////        when(securityUtil.getCryptedValue(anyString())).thenReturn(expectedPassword);
//
////        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//
//        //add valores para as variaveis que são carregadas durante a inicializacao da classe
//        //sendo que durante o teste estas variaveis estao nulas
//        ReflectionTestUtils.setField(dataSourceConfig, "driverClassName", testString);
//        ReflectionTestUtils.setField(dataSourceConfig, "url", testString);
//        ReflectionTestUtils.setField(dataSourceConfig, "username", testString);
//        ReflectionTestUtils.setField(dataSourceConfig, "password", expectedPassword);
//        ReflectionTestUtils.setField(dataSourceConfig, "poolName", testString);
//        ReflectionTestUtils.setField(dataSourceConfig, "minumunIdle", testNumber.intValue());
//        ReflectionTestUtils.setField(dataSourceConfig, "maximunPoolSize", testNumber.intValue());
//        ReflectionTestUtils.setField(dataSourceConfig, "connectionTimeout", testNumber.longValue());
//        ReflectionTestUtils.setField(dataSourceConfig, "idleTimeout", testNumber.longValue());
//        ReflectionTestUtils.setField(dataSourceConfig, "maxLifetime", testNumber.longValue());
//
//
////        HikariDataSource returnedDataSource = (HikariDataSource) dataSourceConfig.getDataSource();
//
//        dataSourceConfig.getDataSource();
//
////        assertEquals(expectedDataSource, returnedDataSource);
//
//        assertNotNull(expectedDataSource);
//
//        //verifica se os valores das variaveis foram inicializados como planejados
////        verify(config).setMinimumIdle(testNumber.intValue());
////        verify(config).setMaximumPoolSize(testNumber.intValue());
////        verify(config).setPoolName(testString);
////        verify(config).setConnectionTimeout(testNumber.longValue());
////        verify(config).setIdleTimeout(testNumber.longValue());
////        verify(config).setMaxLifetime(testNumber.longValue());
////        verify(config).setJdbcUrl(testString);
////        verify(config).setDriverClassName(testString);
//////        verify(config).setUsername(testString);
////
////        verify(config).setPassword(expectedPassword);
//    }
//}