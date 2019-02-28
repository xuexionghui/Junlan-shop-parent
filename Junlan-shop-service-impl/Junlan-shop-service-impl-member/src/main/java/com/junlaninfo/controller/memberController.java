package com.junlaninfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junlaninfo.feign.weixinFeign;
import com.junlaninfo.member.api.weiXinFeign;

@RestController
public class memberController  implements  weiXinFeign{


    
	@Autowired
	weixinFeign   weiXinFeign1;
	@GetMapping("/getMember")
	public String memberInvokeWeixin() {
		String string = weiXinFeign1.getWeixin();
		return string+"123";
	}
	
	

}
