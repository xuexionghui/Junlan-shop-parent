package com.junlaninfo.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.junlaninfo.UserEntity;
import com.junlaninfo.base.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(tags="查询手机号码有无注册")
public interface existPhone {
	
	/*
	 * 查询手机号码有无进行注册
	 */
	@ApiOperation(value="根据手机号码查询是否已经进行注册")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="mobile",dataType="String",required=true,
				value="手机号码")
	})
	@PostMapping(value="existPhone")                    
	BaseResponse<UserEntity>   existPhone(@RequestParam(value="mobile")String mobile);

}
