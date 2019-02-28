package com.junlaninfo.weixin.api;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="微信服务接口")
public interface getWeiXinUser {
	
	@ApiOperation(value="获取到微信用户的名字和姓名")
	@GetMapping("/getWeiXin")
	public String getWeixin();

}
