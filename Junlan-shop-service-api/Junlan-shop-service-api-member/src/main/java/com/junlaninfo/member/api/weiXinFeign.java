package com.junlaninfo.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="΢�ŷ���ӿ�")
public interface weiXinFeign   {
	
	
	/*
	 * member������Ľӿ�
	 */
	@ApiOperation(value="��Ա�������΢��")
	public String   memberInvokeWeixin();

}
