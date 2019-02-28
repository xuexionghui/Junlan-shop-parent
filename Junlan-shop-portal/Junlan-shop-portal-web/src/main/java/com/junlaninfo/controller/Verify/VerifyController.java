package com.junlaninfo.controller.Verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.junlaninfo.utils.RandomValidateCodeUtil;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月29日 下午4:15:53 

* 类说明    输出随机码图片

*/ 
@Controller
public class VerifyController {
	
	@RequestMapping(value = "/getVerify")
	public  void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandcode(request, response);// 输出验证码图片方法
		} catch (Exception e) {

		}
	}
		
	}


