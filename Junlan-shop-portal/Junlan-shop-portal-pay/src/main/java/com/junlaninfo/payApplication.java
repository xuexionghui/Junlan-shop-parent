package com.junlaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.netflix.discovery.shared.Application;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月5日 下午2:40:23 

* 类说明      支付网页启动类

*/ 
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient    //开启eureka客户端功能
public class payApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(payApplication.class);
		springApplication.run(args);

	}

}