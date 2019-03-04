package com.junlaninfo.base.controller;

import org.springframework.ui.Model;

import com.junlaninfo.base.BaseResponse;
import com.junlaninfo.base.Constants;

public class BaseWebController {
	/**
	 * 500页面
	 */
	protected static final String ERROR_500_FTL = "500.ftl";
	
	String LOGIN_QQ_OPENID = "qq_openid";

	// 用户信息不存在
	Integer HTTP_RES_CODE_NOTUSER_203 = 203;

	public Boolean isSuccess(BaseResponse<?> baseResp) {
		if (baseResp == null) {
			return false;
		}
		if (!baseResp.getCode().equals(Constants.HTTP_RES_CODE_200)) {
			return false;
		}
		return true;
	}

	public void setErrorMsg(Model model, String errorMsg) {
		model.addAttribute("error", errorMsg);
	}

}