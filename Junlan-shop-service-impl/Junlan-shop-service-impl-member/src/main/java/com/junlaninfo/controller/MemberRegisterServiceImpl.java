package com.junlaninfo.controller;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.UserEntity;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.mapper.UserMapper;
import com.junlaninfo.member.api.MemberRegisterService;
import com.junlaninfo.member.utils.RedisUtil;
import com.junlaninfo.utils.MD5Util;


@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject>  implements MemberRegisterService{
  
	@Autowired
	private UserMapper  userDao;
	@Autowired
	private existPhoneServiceImpl  checkUser;
	@Autowired
	private RedisUtil   redisUtil;
	@Override
	public BaseResponse<JSONObject> register(@RequestBody UserEntity userEntity, String registCode) {
	    //1、先进行判空处理
		/*if(StringUtils.isEmpty(userEntity.getUserName())) {
			return setResultError("用户名称不能为空!");
		}*/
		if(StringUtils.isEmpty(userEntity.getMobile())) {
			return setResultError("电话号码不能为空!");
		}
		java.util.Random random = new java.util.Random();
		int a = random.nextInt(9000) + 1000;
		if(StringUtils.isEmpty(userEntity.getEmail())) {
			userEntity.setEmail(a+"69152@qq.com");
		}
		//2、判断数据库中是否已经存在这个用户
		BaseResponse<UserEntity> response = checkUser.existPhone(userEntity.getMobile());
		UserEntity userEntity2 = response.getData();
		if(userEntity2!=null) {
			return setResultError("这个手机号码已经注册，请登录");
		}
		System.out.println("注册的号码"+userEntity.getMobile());
		//3、从redis中获取注册码，判断注册码是否正确
		String redisCode = redisUtil.getString(Constants.WEIXINCODE_KEY+userEntity.getMobile());
		System.out.println("输入的注册码"+registCode);
		System.out.println("从Redis中拿到的注册码"+redisCode);
		
		if(!redisCode.equals(registCode)) {
			return setResultError("注册码错误，请输入正确的注册码");
		}
		//验证之后，将注册码注销
		redisUtil.setString(Constants.WEIXINCODE_KEY+userEntity.getMobile(), "已经注销", (long) 0);
		//4、加密密码
		String newPassword = MD5Util.MD5(userEntity.getPassword());
		userEntity.setPassword(newPassword);
		//5、将数据存入到数据库中
		int i = userDao.insertUser(userEntity);
		if(i>0) {
			return setResultSuccess("用户注册成功");
		}else {
			return setResultError("用户注册失败");
		}
	}

	

}
