package com.junlaninfo.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.UserEntity;
import com.junlaninfo.Vo.RegisterVo;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;
import com.junlaninfo.base.controller.BaseWebController;
import com.junlaninfo.feign.memberRegisterFeign;
import com.junlaninfo.utils.JunlanVoToDtoUtils;



/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月28日 下午10:47:09 

* 类说明   注册类

*/
@Controller
public class registController     extends  BaseWebController{
	
	@Autowired
	private memberRegisterFeign   registerfeign;
	@GetMapping("/register")
	public String  register() {
		return "member/register";
	}
	
	@PostMapping("/register")    //@ModelAttribute 借助springmvc的内建机制，把表单转换为vo
	                             //要求表单的属性要和vo对象中的属性一一对应
	public String  postRegister(@ModelAttribute("registerVo") @Validated RegisterVo  registerVo,
			BindingResult result,Model model ) {
		
		//1 将参数返回到结果中
		if(result.hasErrors()) {
			String defaultMessage = result.getFieldError().getDefaultMessage();   //获得第一个错误的消息
			setErrorMsg(model, defaultMessage);
			return "member/register";  //有错误，跳转到注册页面
		}
		
		//2.调用接口
		String voMobile = registerVo.getMobile();
		System.out.println("我是从vo里面拿出来的电话号码"+voMobile );
		UserEntity voToDto = JunlanVoToDtoUtils.voToDto(registerVo,UserEntity.class );
		voToDto.setMobile(voMobile);
		System.out.println("我是电话号码"+voToDto.getMobile() );
		try {
			
			BaseResponse<JSONObject> response = registerfeign.register(voToDto, registerVo.getRegistCode());
			if (!response.getCode().equals(Constants.HTTP_RES_CODE_200)) {
				model.addAttribute("error", response.getMsg());
				return "member/register";    //有错误，跳转到登陆页面
			}
			
		}catch (Exception e) {
			model.addAttribute("error", "系统错误");
			return "member/register";    //有错误，跳转到登陆页面
		}
		//3、跳转到登录页面
		return "member/login";   //注册成功，跳转到登陆页面
	}

}

 