package com.junlaninfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.Vo.LoginVo;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.base.constats.WebConstants;
import com.junlaninfo.base.controller.BaseWebController;
import com.junlaninfo.feign.QQAuthoriServiceFeign;
import com.junlaninfo.feign.memberLoginFeign;
import com.junlaninfo.inputDTO.UserLoginInpDTO;
import com.junlaninfo.utils.CookieUtils;
import com.junlaninfo.utils.JunlanVoToDtoUtils;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月1日 上午11:33:32 

* 类说明    qq联合登陆   授权和回调

*/ 
@Controller
public class QQAuthoriController  extends BaseWebController {
	
	@Autowired
	private  QQAuthoriServiceFeign   qqAuthoriServiceFeign;
	
	@Autowired
	private  memberLoginFeign  loginFeign;
	
	
	/**
	 * 重定向到首页
	 */
	private static final String REDIRECT_INDEX = "redirect:/";   //重定向到首页
	private static final String MB_QQ_QQLOGIN = "member/qqlogin";
	
	/*
	 * 1、获取授权链接
	 */
	@RequestMapping(value="/qqAuth")
	public     String qqAuth(HttpServletRequest request) {
		try {
			//获得授权回调链接
			String authorizeURL = new Oauth().getAuthorizeURL(request);
			return  "redirect:"+authorizeURL;
		} catch (Exception e) {  //有异常返回到错误页面
			return  ERROR_500_FTL;
		}
	}
	
	
	/*
	 * 2、qq授权回调
	 */
	@RequestMapping("/qqLoginBack")
	public String qqLoginBack(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		 try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			if(accessTokenObj==null) {
				return  ERROR_500_FTL;
			}
			
			 String accessToken = accessTokenObj.getAccessToken();
			 if(StringUtils.isEmpty(accessToken)) {
				return  ERROR_500_FTL;
			 }
			 
			  // 利用获取到的accessToken 去获取当前用的openid -------- start
             OpenID openIDObj =  new OpenID(accessToken);
             String openID = openIDObj.getUserOpenID();
             
             //根据openId 去数据库中查询
             BaseResponse<JSONObject> findUserByOpenId = qqAuthoriServiceFeign.findUserByOpenId(openID);
             Integer code = findUserByOpenId.getCode();
             if(code.equals(Constants.HTTP_RES_CODE_NOTUSER_203)) {   //没有绑定
            	 
            	// 使用openid获取用户信息
 				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
 				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
 				if (userInfoBean == null) {
 					return ERROR_500_FTL;
 				}
 				String avatarURL100 = userInfoBean.getAvatar().getAvatarURL100();
 				// 返回用户头像页面展示
 				request.setAttribute("avatarURL100", avatarURL100);
 				httpSession.setAttribute(WebConstants.LOGIN_QQ_OPENID, openID);
 				return MB_QQ_QQLOGIN;
            	 
             }
          // 自动实现登陆
 			JSONObject data =findUserByOpenId.getData();
 			String token = data.getString("token");
 			CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
 			return REDIRECT_INDEX;
             
			 
		} catch (QQConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR_500_FTL;   //出现异常，返回到错误的页面
			
		}
	}
	
	@RequestMapping("/qqJointLogin")
	public String qqJointLogin(@ModelAttribute("loginvo") LoginVo  loginVo,
			HttpServletRequest request,
			HttpServletResponse  response,
			HttpSession session,
			Model  model) {
		Object attribute = session.getAttribute(WebConstants.LOGIN_QQ_OPENID);
		//从session中回调方法中 将qqOpenId存入到session中
		String qqOpenId=(String)attribute;
		//将loginvo转为LoginDto,并将QQOpenId存储进去
		UserLoginInpDTO userLoginInpDTO = JunlanVoToDtoUtils.voToDto(loginVo, UserLoginInpDTO.class);
		userLoginInpDTO.setQqOpenId(qqOpenId);   //设置qqOpenId
		userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
		//String info = webBrowserInfo(request);
		userLoginInpDTO.setDeviceInfor("火狐浏览器");
		
		//调用member服务，进行登录
		 BaseResponse<JSONObject> userLoginResponse = loginFeign.userLogin(userLoginInpDTO);
		 
		if(!userLoginResponse.getCode().equals(Constants.HTTP_RES_CODE_200)){
			setErrorMsg(model, userLoginResponse.getMsg());
			return MB_QQ_QQLOGIN;   //返回到登录页面
			
		}
		 JSONObject data = userLoginResponse.getData();
		 String token = data.getString("token");
		 CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
		 return REDIRECT_INDEX;
	}
	
	
	/**
	 * 获取浏览器信息
	 * 
	 * @return
	 */
	/*public String webBrowserInfo(HttpServletRequest request) {
		// 获取浏览器信息
		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
		// 获取浏览器版本号
		Version version = browser.getVersion(request.getHeader("User-Agent"));
		String info = browser.getName() + "/" + version.getVersion();
		return info;
	}
	*/

	
	
	

}