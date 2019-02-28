package com.junlaninfo.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.junlaninfo.weixin.api.getWeiXinUser;

@FeignClient(name="app-mayikt-weixin")
public interface weixinFeign  extends getWeiXinUser{

}
