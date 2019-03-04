package com.junlaninfo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.DO.UserDo;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.inputDTO.UserLoginInpDTO;
import com.junlaninfo.mapper.UserMapper;
import com.junlaninfo.member.api.xxl_sso;
import com.junlaninfo.outputDTO.UserOutDTO;
import com.junlaninfo.utils.JunlanVoToDtoUtils;
import com.junlaninfo.utils.MD5Util;
import com.junlaninfo.utils.junlanBeanUtils;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月4日 下午7:45:15 

* 类说明 

*/ 
@RestController
public class xxl_ssoImpl     extends BaseApiService<UserOutDTO>   implements xxl_sso{
	
	@Autowired
	private  UserMapper  userMapper;

	@Override
	public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO) {
		
		// 1.验证参数
				String mobile = userLoginInpDTO.getMobile();
				if (StringUtils.isEmpty(mobile)) {
					return setResultError("手机号码不能为空!");
				}
				String password = userLoginInpDTO.getPassword();
				if (StringUtils.isEmpty(password)) {
					return setResultError("密码不能为空!");
				}
				// 判断登陆类型
				String loginType = userLoginInpDTO.getLoginType();
				if (StringUtils.isEmpty(loginType)) {
					return setResultError("登陆类型不能为空!");
				}
				// 目的是限制范围
				if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
						|| loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
					return setResultError("登陆类型出现错误!");
				}

				// 设备信息
				String deviceInfor = userLoginInpDTO.getDeviceInfor();
				if (StringUtils.isEmpty(deviceInfor)) {
					return setResultError("设备信息不能为空!");
				}
				// 2.对登陆密码实现加密
				String newPassWord = MD5Util.MD5(password);
				// 3.使用手机号码+密码查询数据库 ，判断用户是否存在
				UserDo userDo = userMapper.login(mobile);
				String dbPassword = userDo.getPassword();
			    System.out.println("数据库里面的密码是："+dbPassword);
			    boolean b = newPassWord.equals(dbPassword);
			    if(userDo==null ||!b) {
					return setResultError("密码或者手机号不正确，请检查后重新输入");
				}
			    
			    
				return setResultSuccess(junlanBeanUtils.doToDto(userDo, UserOutDTO.class));
	}

}