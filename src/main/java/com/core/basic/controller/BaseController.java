package com.core.basic.controller;

import com.core.basic.result.BaseResult;
import com.core.constance.ApiConstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	/**
	 * API接口返回成功的实体
	 * @param resultData 实体数据
	 * @return ResponseEntity<BaseResult>
	 */
	protected ResponseEntity<BaseResult> buildSuccessInfo(Object resultData) {
		BaseResult result = new BaseResult(ApiConstance.BASE_SUCCESS_CODE, ApiConstance.getMessage(ApiConstance.BASE_SUCCESS_CODE), resultData);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	
	/**
	 * API接口返回失败的实体
	 * @param errorCode 错误代码号
	 * @return ResponseEntity<BaseResult>
	 */
	protected ResponseEntity<BaseResult> buildFailedInfo(int errorCode) {
		BaseResult result = new BaseResult(errorCode, ApiConstance.getMessage(errorCode), null);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}

	/**
	 * API接口返回失败的实体
	 * @param errorCode 错误代码号
	 * @param appendMsg 错误信息尾部需要拼接的信息
	 * @return
	 */
	protected ResponseEntity<BaseResult> buildFailedInfo(int errorCode, String appendMsg) {
		BaseResult result = new BaseResult(errorCode, ApiConstance.getMessage(errorCode)+appendMsg, null);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	
	/**
	 * 方法名：buildFailedInfo
	 * 详述：API接口返回失败的实体
	 * @param errorMsg 自定义错误提示
	 * @return
	 */
	protected ResponseEntity<BaseResult> buildFailedInfo(String errorMsg) {
		BaseResult result = new BaseResult(ApiConstance.BASE_FAIL_CODE, errorMsg, null);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	

	
}
