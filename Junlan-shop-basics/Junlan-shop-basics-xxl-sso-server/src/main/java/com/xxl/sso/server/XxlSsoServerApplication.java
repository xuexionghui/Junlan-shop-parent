package com.xxl.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xuxueli 2018-03-22 23:41:47
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient  //开启eureka的客户端
@EnableFeignClients     //开启feign客户端远程调用的功能
public class XxlSsoServerApplication {
	public static void main(String[] args) {
        SpringApplication.run(XxlSsoServerApplication.class, args);
	}

}