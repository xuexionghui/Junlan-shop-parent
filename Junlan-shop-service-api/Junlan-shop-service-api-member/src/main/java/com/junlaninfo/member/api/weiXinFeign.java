package com.junlaninfo.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="微信服务接口")
public interface weiXinFeign   {
	
	
	/*
	 * member服务本身的接口
	 */
	@ApiOperation(value="会员服务调用微信")
	public String   memberInvokeWeixin();

}
