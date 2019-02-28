package com.junlaninfo.GlobalExceptionHandler;

import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.junlaninfo.base.BaseApiService;
import com.junlaninfo.base.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice   //相当于一个component，  作用：定义一个exceptionHandler
@Slf4j   //开启日志
public class GlobalExceptionHandler extends BaseApiService<JSONObject> {
	
	
	@ExceptionHandler(value= Exception.class)   //在发生异常的时候，使用下面这个方法捕获异常
	@ResponseBody
	public BaseResponse<JSONObject> exceptionHandler(Exception e) {
		log.info("###全局捕获异常###,error:{}", e);
		return setResultError("系统错误，请稍后再试");
	}

}
