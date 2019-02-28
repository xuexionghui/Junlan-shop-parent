package com.github.binarywang.demo.wx.mp.handler;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import com.github.binarywang.demo.wx.mp.feign.weixinInvokeMemberFeign;
import com.github.binarywang.demo.wx.mp.utils.JsonUtils;
import com.github.binarywang.demo.wx.mp.utils.RedisUtil;
import com.junlaninfo.UserEntity;
import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;

import com.junlaninfo.utils.RegexUtils;


import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {
	
	/**
	 * 发送验证码消息
	 */
	@Value("${mayikt.weixin.registration.code.message}")
	private String registrationCodeMessage;
	/**
	 * 默认回复消息
	 */
	@Value("${mayikt.weixin.default.registration.code.message}")
	private String defaultRegistrationCodeMessage;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private weixinInvokeMemberFeign   memberFeign;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
         //获取从微信服务器发来的消息
        String string = wxMessage.getContent();
        //1、检查发来的消息是不是手机号码
        if(RegexUtils.checkMobile(string)) {
        	//2、检查该手机号码是否已经注册，如果已经注册，那么直接返回登录
        	BaseResponse<UserEntity> response = memberFeign.existPhone(string);
        	if(response.getData()!=null) { //查询到用户
        		return new TextBuilder().build("该手机号已经注册，请登录", wxMessage, weixinService);	
        	}
        	//3、如果是，那么调用验证码生成方法
        	int code=code();
        	String content = String.format(registrationCodeMessage, code);
        	//4、将验证码存入redis中
        	redisUtil.setString(Constants.WEIXINCODE_KEY + string, code + "", Constants.WEIXINCODE_TIMEOUT);
        	return new TextBuilder().build(content, wxMessage, weixinService);	
        }
        //TODO 组装回复消息
       // String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);

    }
    
    /*
     * 利用math类生成注册码
     */
    public int  code() {
    	int code=(int) (Math.random()*9000+1000);
    	return code;
    }
   

}
