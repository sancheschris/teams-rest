package com.tempo.teams.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataSourceConfig.class)
public class DataSourceConfigTest {

    @Test
    public void getDataSourceArqrShouldReturnDataSource() throws Exception {
        String expectedPassword = "1234";
        String testString = "test";
        Number testNumber = 1;
        //cria a instancia do objeto de conexao do banco
        HikariConfig config = mock(HikariConfig.class);

        HikariDataSource expectedDataSource = mock(HikariDataSource.class);

        //mock when the Hikari in the datasource class is running
        whenNew(HikariConfig.class).withNoArguments().thenReturn(config);
        whenNew(HikariDataSource.class).withArguments(config).thenReturn(expectedDataSource);


        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        //setting environments variables
        ReflectionTestUtils.setField(dataSourceConfig, "driverClassName", testString);
        ReflectionTestUtils.setField(dataSourceConfig, "url", testString);
        ReflectionTestUtils.setField(dataSourceConfig, "username", testString);
        ReflectionTestUtils.setField(dataSourceConfig, "password", expectedPassword);
        ReflectionTestUtils.setField(dataSourceConfig, "poolName", testString);
        ReflectionTestUtils.setField(dataSourceConfig, "minumunIdle", testNumber.intValue());
        ReflectionTestUtils.setField(dataSourceConfig, "maximunPoolSize", testNumber.intValue());
        ReflectionTestUtils.setField(dataSourceConfig, "connectionTimeout", testNumber.longValue());
        ReflectionTestUtils.setField(dataSourceConfig, "idleTimeout", testNumber.longValue());
        ReflectionTestUtils.setField(dataSourceConfig, "maxLifetime", testNumber.longValue());
        ReflectionTestUtils.setField(dataSourceConfig, "profile", testString);


        // Action
        HikariDataSource returnedDataSource = (HikariDataSource) dataSourceConfig.getDataSource();

        // validations
        assertEquals(expectedDataSource, returnedDataSource);

        assertNotNull(expectedDataSource);

        verify(config).setMinimumIdle(testNumber.intValue());
        verify(config).setMaximumPoolSize(testNumber.intValue());
        verify(config).setPoolName(testString);
        verify(config).setConnectionTimeout(testNumber.longValue());
        verify(config).setIdleTimeout(testNumber.longValue());
        verify(config).setMaxLifetime(testNumber.longValue());
        verify(config).setJdbcUrl(testString);
        verify(config).setDriverClassName(testString);
        verify(config).setUsername(testString);
        verify(config).setPassword(expectedPassword);
    }
}