package com.junlaninfo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.DO.UserDo;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.mapper.UserMapper;
import com.junlaninfo.member.api.QQAuthoriService;
import com.junlaninfo.member.utils.GenerateToken;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月1日 下午3:34:17 

* 类说明 

*/ 

@RestController
public class QQAuthoriServiceImpl   extends BaseApiService<JSONObject>   implements  QQAuthoriService{
   
	 @Autowired
	 private UserMapper   userMapper;  
	 @Autowired
	 private GenerateToken generateToken;
	 
	@Override
	public BaseResponse<JSONObject> findUserByOpenId(String qqOpenId) {
		if(StringUtils.isEmpty(qqOpenId)) {
			return setResultError("qqOpenId不能为空!");
		}
		
		UserDo userDo = userMapper.findByOpenId(qqOpenId);
		//1.判断userDo是为 空
		if(userDo==null) {
			return setResultError(Constants.HTTP_RES_CODE_NOTUSER_203, "根据qqOpenId没有查询到用户信息");
		}
		
		//查询到，证明该账号已经绑定过了，生成token，自动登录
		// 2.如果能够查询到用户信息,则直接生成对应的用户令牌
		String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + Constants.HTTP_RES_CODE_QQ_LOGINTYPE;
		Long userId = userDo.getUserId();
		String userToken = generateToken.createToken(keyPrefix, userId + "");
		JSONObject data = new JSONObject();
		data.put("token", userToken);
		return setResultSuccess(data);
	}

}