package com.spring.main;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.*"})
@EntityScan("com.spring.entities")
public class DeTaiQuanLyThuBongApplication extends SpringBootServletInitializer{

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(DeTaiQuanLyThuBongApplication.class);
		
	}

	public static void main(String[] args) {
		
		SpringApplication.run(DeTaiQuanLyThuBongApplication.class, args);
		
	}

}
