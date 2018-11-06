package com.fsd.finalproj.projectmanagerapi;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.fsd.finalproj.projectmanagerapi.dao")
@EntityScan(basePackages="com.fsd.finalproj.projectmanagerapi")
@EnableTransactionManagement
public class ApplicationConfiguration {
}
