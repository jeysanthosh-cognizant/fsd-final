package com.fsd.finalproj.projectmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ProjectManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApiApplication.class, args);
	}
}
