package com.junlaninfo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.junlaninfo.UserEntity;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.mapper.UserMapper;
import com.junlaninfo.member.api.existPhone;

@RestController
public class existPhoneServiceImpl  extends BaseApiService<UserEntity> implements existPhone{
   
	@Autowired
	private UserMapper  userDao;
	@Override
	public BaseResponse<UserEntity> existPhone(String mobile) {
		if(StringUtils.isEmpty(mobile)) {
		  return setResultError("手机号码不能为空");
		}
		UserEntity userEntity = userDao.existMobile(mobile);
		//判断用户是否为空
		if(userEntity==null) {
		  return setResultError("用户没有注册");
		}
		
		//应该对会员的密码进行脱密，防止密码泄露
		userEntity.setPassword(null);   //将返回数据中会员的密码置为null
;		return setResultSuccess(userEntity);
	}

}
