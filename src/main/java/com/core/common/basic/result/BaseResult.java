package com.core.common.basic.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "BaseResult", description = "返回集合")
public class BaseResult {
	
	@ApiModelProperty(notes = "返回码，0表示成功，非0表示失败")
	private Integer resultCode;

	@ApiModelProperty(notes = "返回消息，成功为\"success\",失败为具体失败信息")
	private String resultMessage;

	// 返回数据
	@ApiModelProperty(notes = "返回的具体数据")
	private Object resultData;
	
	public BaseResult() {
		super();
	}
	
	public BaseResult(Integer resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public BaseResult(Object resultData) {
		super();
		this.resultData = resultData;
	}
	

	public BaseResult(Integer resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public BaseResult(Integer resultCode, String resultMessage, Object resultData) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.resultData = resultData;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	
	
}
