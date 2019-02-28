package com.junlaninfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig   //开启apollo的功能
public class zuulApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(zuulApplication.class);
		application.run(args);
	}
	
	// 添加文档来源
		@Component
		@Primary
		class DocumentationConfig implements SwaggerResourcesProvider {
			@Override
			public List<SwaggerResource> get() {
				List resources = new ArrayList<>();
				// app-itmayiedu-order
				resources.add(swaggerResource("app-mayikt-member", "/app-mayikt-member/v2/api-docs", "2.0"));
				resources.add(swaggerResource("app-mayikt-weixin", "/app-mayikt-weixin/v2/api-docs", "2.0"));
				return resources;
			}

			private SwaggerResource swaggerResource(String name, String location, String version) {
				SwaggerResource swaggerResource = new SwaggerResource();
				swaggerResource.setName(name);
				swaggerResource.setLocation(location);
				swaggerResource.setSwaggerVersion(version);
				return swaggerResource;
			}

		}

}
