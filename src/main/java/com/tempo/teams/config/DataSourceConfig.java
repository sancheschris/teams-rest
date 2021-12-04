package com.tempo.teams.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minumunIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximunPoolSize;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private Long connectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private Long idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private Long maxLifetime;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setMinimumIdle(minumunIdle);
        config.setMaximumPoolSize(maximunPoolSize);
        config.setPoolName(poolName);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setJdbcUrl(url);
        config.setDriverClassName(driverClassName);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }
}
