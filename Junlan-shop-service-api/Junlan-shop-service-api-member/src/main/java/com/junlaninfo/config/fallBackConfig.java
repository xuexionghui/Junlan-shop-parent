package com.junlaninfo.config;

import org.springframework.stereotype.Component;

//@Component
public class fallBackConfig {
      
	public String  fallback() {
		return "微信服务调用失败，请再试一次";
	}
}
