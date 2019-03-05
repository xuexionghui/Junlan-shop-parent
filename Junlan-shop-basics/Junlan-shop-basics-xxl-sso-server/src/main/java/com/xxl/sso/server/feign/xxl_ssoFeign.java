package com.xxl.sso.server.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.junlaninfo.member.api.xxl_sso;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月5日 下午4:54:10 

* 类说明 

*/ 
@FeignClient(name="app-mayikt-member")
public interface xxl_ssoFeign   extends xxl_sso {

}