package com.junlaninfo.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.junlaninfo.member.api.MemberRegisterService;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月29日 下午8:48:10 

* 类说明 

*/ 
@FeignClient(name="app-mayikt-member")
public interface memberRegisterFeign  extends MemberRegisterService{

}
