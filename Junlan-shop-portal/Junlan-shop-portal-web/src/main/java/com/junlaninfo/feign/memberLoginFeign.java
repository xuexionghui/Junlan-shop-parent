package com.junlaninfo.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.junlaninfo.member.api.MemberLoginService;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月30日 下午3:28:22 

* 类说明 

*/ 
@FeignClient(name="app-mayikt-member")
public interface memberLoginFeign extends MemberLoginService {

}
