package com.xxl.sso.server.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.junlaninfo.member.api.xxl_sso;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月4日 下午8:03:24 

* 类说明 

*/ 
@FeignClient(name="app-mayikt-member")
public interface xxl_ssoFeign   extends  xxl_sso{

}