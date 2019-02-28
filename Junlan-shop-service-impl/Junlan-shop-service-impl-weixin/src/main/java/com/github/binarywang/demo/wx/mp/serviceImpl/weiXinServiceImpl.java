package com.github.binarywang.demo.wx.mp.serviceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junlaninfo.AppEntity;
import com.junlaninfo.weixin.api.getWeiXinUser;
import com.netflix.infix.lang.infix.antlr.EventFilterParser.regex_predicate_return;

@RestController
public class weiXinServiceImpl  implements getWeiXinUser{
   
	@GetMapping("/getWeiXin")
	public String getWeixin() {
		AppEntity user = new AppEntity();
		user.setAppId("001");
	    user.setAppSecret("雪熊晖");
		return user.getAppId()+"姓名："+user.getAppSecret();
	}
}
