package com.github.binarywang.demo.wx.mp.serviceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.demo.wx.mp.utils.RedisUtil;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.weixin.api.VerificaCodeService;

@RestController
public class VerificaCodeServiceImpl extends BaseApiService<JSONObject>   implements VerificaCodeService{
   
	@Autowired
	private  RedisUtil redisUtil;
	@Override
	@GetMapping("/verifyWeixinCode")
	public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {
		//1、判断是否为空
		if(StringUtils.isEmpty(phone)) {
			return setResultError( "手机号码不能为空");
		}
		if(StringUtils.isEmpty(phone)) {
			return setResultError("注册码不能为空，请输入");
		}
		//2、从redis中取出验证码，判断是否为空
		String redisKey=Constants.WEIXINCODE_KEY+phone;
		String redisCode = redisUtil.getString(redisKey);
		if (StringUtils.isEmpty(redisCode)) {
			return setResultError("验证码已经过期，或者您的手机还没有注册，请注册");
		}
		//3、进行对比
		if(!redisCode.equals(weixinCode)) {
			return setResultError("验证码不正确，请重新输入");
		}
		
		//4、返回正确结果
		return setResultSuccess("注册码验证码正确");
	}

}
