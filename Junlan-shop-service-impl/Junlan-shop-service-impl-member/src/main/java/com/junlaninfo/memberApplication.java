package com.junlaninfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients   //开启feign客户端
@EnableSwagger2
//@EnableApolloConfig   先不使用 阿波罗的配置
@MapperScan(basePackages="com.junlaninfo.mapper")
public class memberApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(memberApplication.class);
		application.run(args);
	}

}
