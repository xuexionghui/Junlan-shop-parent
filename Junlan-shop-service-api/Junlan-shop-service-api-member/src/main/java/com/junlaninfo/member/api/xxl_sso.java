package com.junlaninfo.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.inputDTO.UserLoginInpDTO;
import com.junlaninfo.outputDTO.UserOutDTO;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月4日 下午7:42:15 

* 类说明    xxl-sso登陆接口

*/ 
public interface xxl_sso {
	
	@PostMapping("/ssoLogin")
	public  BaseResponse<UserOutDTO>   ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO);

}
