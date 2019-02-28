package com.junlaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer    //开启Eureka服务注册中心
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class eurekaApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(eurekaApplication.class);
		application.run(args);
	}

}
