package com.junlaninfo.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.outputDTO.UserOutDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月28日 下午5:10:47 

* 类说明 

*/ 
@Api(tags="通过token找到会员的信息")
public interface TokenFindMemberService {
	@ApiOperation(value="通过token找到会员")
	@PostMapping(value="/useTokenGetMember")
	public BaseResponse<UserOutDTO>  useTokenGetMember(@RequestParam String token );

}

