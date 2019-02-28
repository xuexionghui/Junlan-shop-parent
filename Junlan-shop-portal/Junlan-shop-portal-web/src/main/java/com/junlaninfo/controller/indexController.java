package com.junlaninfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月28日 下午10:43:38 

* 类说明    网站首页

*/ 
@Controller
public class indexController {
	
	/*
	 * 跳转到首页
	 */
	@RequestMapping("/")
	public String  index() {
		return "index";
	}
	
	/*
	 * 跳转到首页
	 */
	@RequestMapping(value="/index.html")
	public String indexHtml() {
		return "index";
	}

}