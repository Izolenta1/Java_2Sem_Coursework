package com.example.courseWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
		exclude = { DataSourceAutoConfiguration.class }
)
public class CourseWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseWorkApplication.class, args);
	}

}
