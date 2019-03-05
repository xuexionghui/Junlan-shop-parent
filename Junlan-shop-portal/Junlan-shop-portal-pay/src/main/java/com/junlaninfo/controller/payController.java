package com.junlaninfo.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月5日 下午2:46:30 

* 类说明   支付网页的controller

*/ 


@Controller
public class payController {
	 
	@RequestMapping(value="/")
	 public String   index() {
		 
		return "index";
	 }

}
