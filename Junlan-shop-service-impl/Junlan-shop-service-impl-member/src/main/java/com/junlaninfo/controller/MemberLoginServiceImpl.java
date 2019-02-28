package com.junlaninfo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.DO.UserDo;
import com.junlaninfo.DO.UserTokenDo;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.inputDTO.UserLoginInpDTO;
import com.junlaninfo.mapper.UserMapper;
import com.junlaninfo.mapper.UserTokenMapper;
import com.junlaninfo.member.api.MemberLoginService;
import com.junlaninfo.member.utils.GenerateToken;
import com.junlaninfo.utils.MD5Util;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月27日 下午8:31:56 

* 类说明 
s
*/ 
@RestController
public class MemberLoginServiceImpl  extends BaseApiService<JSONObject> implements  MemberLoginService{
    
	@Autowired
	private  UserMapper  userMapper; 
	@Autowired
	private GenerateToken  generateToken;
	@Autowired
	private  UserTokenMapper  usertokenMapper;
	@Override
	public BaseResponse<JSONObject>  userLogin(@RequestBody UserLoginInpDTO userLoginInpDTO) {
		//1、判空
		if(StringUtils.isEmpty(userLoginInpDTO.getMobile())||StringUtils.isEmpty(userLoginInpDTO.getPassword())) {
			return setResultError("手机号码或者密码不能为空，请输入");
		}
		String mobile = userLoginInpDTO.getMobile();
		String password = userLoginInpDTO.getPassword();
		String newPassword = MD5Util.MD5(password);   //加密密码
		
		String loginType = userLoginInpDTO.getLoginType();
		if (StringUtils.isEmpty(loginType)) {
			return setResultError("登陆类型不能为空!");
		}
		if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
				|| loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
			return setResultError("登陆类型出现错误!");
		}
		//2、查询  在注册的时候规定了，一个手机号码只能注册一次，
		//因此在登陆的时候可以采用手机号码先去查询表，然后对比密码进行登录的方式
		UserDo userDo = userMapper.login(mobile);
	    String dbPassword = userDo.getPassword();
	    System.out.println("数据库里面的密码是："+dbPassword);
	    boolean b = newPassword.equals(dbPassword);
	    if(userDo==null ||!b) {
			return setResultError("密码或者手机号不正确，请检查后重新输入");
		}
		//4、生成token   将表里面的userId作为token的value
	    Long userId = userDo.getUserId();
	    String  keyPrefix=Constants.MEMBER_TOKEN_KEYPREFIX;
	    String  redisValue=userId+"";
	    
	    
	    UserTokenDo userTokenDo =usertokenMapper.selectByUserIdAndLoginType(userId);
	    String token =null;
	    if(userTokenDo==null) {
	        Long time=Constants.MEMBRE_LOGIN_TOKEN_TIME;
	 	    token = generateToken.createToken(keyPrefix, redisValue, time);
		 	 //5、将token存入到数据库
				//userTokenDo.setToken(token);
				  //设置设备型号
				//userTokenDo.setDeviceInfor(userLoginInpDTO.getDeviceInfor());
				UserTokenDo userTokenDo2 = new  UserTokenDo();
				userTokenDo2.setUserId(userDo.getUserId());
				userTokenDo2.setDeviceInfor(userLoginInpDTO.getDeviceInfor());
				userTokenDo2.setLoginType(userLoginInpDTO.getLoginType());
				userTokenDo2.setToken(token);
				usertokenMapper.insertLoginToken(userTokenDo2);
	    }else {
	    	String dbLoginType = userTokenDo.getLoginType();   //查询到数据库里面的登陆类型
		    boolean c = loginType.equals(dbLoginType);         //判断登录的类型是否一致
		    token=usertokenMapper.selectByUserIdAndLoginType(userId).getToken();
		    if (c) {
				// 4.清除之前的token
				String tokenBefore = userTokenDo.getToken();
				Boolean removeToken = generateToken.removeToken(tokenBefore);
				
				if (removeToken) {
					usertokenMapper.updateTokenAvailability(userId);
					
				}
			}
		}
	    
	   
	   
		
	    JSONObject data = new JSONObject();
		data.put("data", token);
		return setResultSuccess(data);
	}
}