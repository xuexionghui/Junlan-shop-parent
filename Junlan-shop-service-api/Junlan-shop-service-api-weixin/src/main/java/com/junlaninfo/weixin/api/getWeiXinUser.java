package com.junlaninfo.weixin.api;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="΢�ŷ���ӿ�")
public interface getWeiXinUser {
	
	@ApiOperation(value="��ȡ��΢���û������ֺ�����")
	@GetMapping("/getWeiXin")
	public String getWeixin();

}
