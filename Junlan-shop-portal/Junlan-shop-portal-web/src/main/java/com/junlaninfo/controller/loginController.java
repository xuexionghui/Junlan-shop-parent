package com.junlaninfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.Vo.LoginVo;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.base.controller.BaseWebController;
import com.junlaninfo.feign.memberLoginFeign;
import com.junlaninfo.inputDTO.UserLoginInpDTO;
import com.junlaninfo.utils.CookieUtils;
import com.junlaninfo.utils.JunlanVoToDtoUtils;
import com.junlaninfo.utils.RandomValidateCodeUtil;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月28日 下午10:46:31 

* 类说明   跳转到登陆页面

*/ 
@Controller
public class loginController  extends  BaseWebController{
	
	@Autowired
	private  memberLoginFeign  loginFeign;
	
	@GetMapping("/login")
	public String  login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String  postLogin(@ModelAttribute("loginVo")  @Validated LoginVo  loginVo,BindingResult result,Model model,HttpSession httpSession,
			HttpServletRequest request,HttpServletResponse response
			) {
		//1、判断验证码是否正确
		String graphicCode = loginVo.getGraphicCode();
		if(!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
			setErrorMsg(model, "图形验证码不正确!");
			return "member/login";
		}
		 
		//2、调用会员的登陆接口
		 UserLoginInpDTO  userLoginInpDTO= JunlanVoToDtoUtils.voToDto(loginVo, UserLoginInpDTO .class);   //将vow转换为dto
		 userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
		 String info = "火狐浏览器";
		 userLoginInpDTO.setDeviceInfor(info);
		 BaseResponse<JSONObject> response1 = loginFeign.userLogin(userLoginInpDTO);
		 System.out.println("返回的数据"+response1);
		 String code = response1.getCode()+"";
		 if(!code.equals("200")) {
			 setErrorMsg(model, response1.getMsg());
			 return "member/login"; 
		 }
		//3、将登陆的token写入到cookie中
		 
		 JSONObject jsonObject = response1.getData();
		 String token  = (String) jsonObject.get("data");
		 System.out.println("这个token是："+token);
		 CookieUtils.setCookie(request, response, "junlan_Cookie", token);
		//4、跳转到首页
		
		return "redirect:/";
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
