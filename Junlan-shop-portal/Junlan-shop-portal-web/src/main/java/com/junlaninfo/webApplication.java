package com.junlaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月28日 下午10:10:22 

* 类说明    portal-web的启动类
*        使用了xxl-sso 做系统的单点登陆系统的话，那么需要有一个配置类去读取
*        单点登陆系统的相关配置类（如和redis的连接、认证授权中心的地址等内容）

*/ 

@SpringBootApplication
@EnableEurekaClient
//@EnableApolloConfig   先不用阿波罗的配置
@EnableFeignClients  //开启feign
public class webApplication {

	public static void main(String[] args) {
		SpringApplication.run(webApplication.class, args);

	}

}