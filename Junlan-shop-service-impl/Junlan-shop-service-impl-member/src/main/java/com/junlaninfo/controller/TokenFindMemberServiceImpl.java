package com.junlaninfo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.DO.UserDo;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.mapper.UserMapper;
import com.junlaninfo.member.api.TokenFindMemberService;
import com.junlaninfo.member.utils.GenerateToken;
import com.junlaninfo.member.utils.RedisUtil;
import com.junlaninfo.outputDTO.UserOutDTO;
import com.junlaninfo.utils.TypeCastHelper;
import com.junlaninfo.utils.junlanBeanUtils;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月28日 下午5:14:41 

* 类说明 

*/ 

@RestController                      //同一个类中，如果有继承和实现，必须先继承，然后再实现接口，不然会报错
public class TokenFindMemberServiceImpl  extends BaseApiService<UserOutDTO>  implements  TokenFindMemberService {
    
	@Autowired
	private   UserMapper  userMapper;
	
	@Autowired
	private  GenerateToken  generateToken;
	
	@Override
	public BaseResponse<UserOutDTO> useTokenGetMember(String token) {
		
		if (token==null) {
			return setResultError("token不能为空");
		}
		 //根据token去redis中找到token的value，因为token的value是 会员的userId+""而成的
		String tokenValue = generateToken.getToken(token);
		if(tokenValue==null) {
			return setResultError("token 不正确或者token已经过期");
		}
		Long userId = TypeCastHelper.toLong(tokenValue);   
		UserDo userDo = userMapper.findByUserId(userId);
		UserOutDTO userOutDTO = junlanBeanUtils.doToDto(userDo, UserOutDTO.class);
		
		
		return setResultSuccess(userOutDTO);
	}

}

