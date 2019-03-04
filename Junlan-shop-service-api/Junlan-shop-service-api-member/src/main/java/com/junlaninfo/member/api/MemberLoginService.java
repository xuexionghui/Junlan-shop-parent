package com.junlaninfo.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.inputDTO.UserInpDTO;
import com.junlaninfo.inputDTO.UserLoginInpDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月27日 下午8:23:47 

* 类说明  这是  会员登陆的接口

*/ 
@Api(tags="会员登陆")
public interface MemberLoginService {
	
	@ApiOperation(value="利用手机号码和密码进行登陆")
	@PostMapping(value="/login1")
	BaseResponse<JSONObject>   userLogin(@RequestBody UserLoginInpDTO userLoginInpDTO);

}
