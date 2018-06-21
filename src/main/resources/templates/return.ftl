package com.java.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.io.Serializable;
<#list table.columnList as col>
<#if col.javaType == "BigDecimal">
import java.math.BigDecimal;
<#break>
</#if>
</#list>

@ApiModel("${table.tableNameCH}")
public class Return${table.shortName} implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("id")
	public String id;
	<#list table.columnList as col>
	<#if col.columnName != "id" && col.columnName != "create_date" && col.columnName != "update_date">
	@ApiModelProperty("${col.columnComment}")
	public ${col.javaType} ${col.attrName};
	</#if>
	</#list>
	@ApiModelProperty("更新时间")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	public Date createDate;
	
	
	<#list table.columnList as col>
	<#if col.columnName != "create_date" && col.columnName != "update_date">
    
    <#if col.javaType == "Date">
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
    public ${col.javaType} ${col.getMethod}() {
    	return ${col.attrName};
    }
    public void ${col.setMethod}(${col.javaType} ${col.attrName}) {
    	this.${col.attrName} = ${col.attrName};
    }
    </#if>
	</#list>
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}