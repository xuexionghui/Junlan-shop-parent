package com.junlaninfo.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.UserEntity;
import com.junlaninfo.base.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="会员接口")
public interface MemberRegisterService {
	
  
	/*
	 * 会员注册
	 */
	@PostMapping(value="/register1")
	@ApiOperation(value = "会员用户注册信息接口")
	public BaseResponse<JSONObject>  register(
			@RequestBody  UserEntity  userEntity,
			@RequestParam(value="registCode")  String registCode);
}
