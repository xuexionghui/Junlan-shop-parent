package com.junlaninfo.weixin.api;

import javax.ws.rs.GET;

import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.base.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="验证注册码正误接口")
public interface VerificaCodeService {
	
	@GetMapping("/verifyWeixinCode")
	@ApiOperation(value="验证注册码正误")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="phone", 
				dataType = "String", required = true, value = "用户手机号码"),
		@ApiImplicitParam(paramType="query",name="weixinCode",
		        dataType="String",required=true,value="微信验证码")
	})
	public BaseResponse<JSONObject>   verificaWeixinCode(String phone, String weixinCode);

}
