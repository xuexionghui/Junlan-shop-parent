package com.github.binarywang.demo.wx.mp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.junlaninfo.UserEntity;
import com.junlaninfo.base.BaseResponse;


@FeignClient(name="app-mayikt-member")
public interface weixinInvokeMemberFeign   {
	/*
	 *   在使用feign进行调用的时候，请求方式应该是Post方式，
	 * 而且如果有参数需要传递的话，那么必须加上@RequestParam注解，不然会报一个错误  参数传递不过去
	 */
	@PostMapping(value="existPhone")
	public BaseResponse<UserEntity> existPhone(@RequestParam(value="mobile")String mobile);
	

}
