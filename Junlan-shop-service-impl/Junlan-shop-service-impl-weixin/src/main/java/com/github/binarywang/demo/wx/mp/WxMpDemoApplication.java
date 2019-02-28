package com.github.binarywang.demo.wx.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
//@EnableApolloConfig   先不使用apollo的配置
@EnableFeignClients   //开启feign客户端
public class WxMpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxMpDemoApplication.class, args);
    }
}
