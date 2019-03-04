package com.junlaninfo.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.junlaninfo.member.api.QQAuthoriService;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年3月1日 下午4:09:14 

* 类说明 

*/ 
@FeignClient(name="app-mayikt-member")
public interface QQAuthoriServiceFeign extends QQAuthoriService  {

}