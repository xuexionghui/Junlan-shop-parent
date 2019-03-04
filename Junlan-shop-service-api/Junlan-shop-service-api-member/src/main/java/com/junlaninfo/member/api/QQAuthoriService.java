package com.junlaninfo.member.api;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.base.BaseResponse;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月1日 下午3:13:35 

* 类说明     根据qq的openId去查询有无绑定，绑定了的话没自动登录

*/ 
public interface QQAuthoriService {
	
	@PostMapping("/findByOpenId")
	public  BaseResponse<JSONObject>     findUserByOpenId(
			@RequestParam("qqOpenId") String qqOpenId
			);

}