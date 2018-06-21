package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;
<#list table.columnList as col>
<#if col.javaType == "BigDecimal">
import java.math.BigDecimal;
<#break>
</#if>
</#list>

public class ${table.className} extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	<#list table.columnList as col>
	<#if col.columnName != "id" && col.columnName != "create_date" && col.columnName != "update_date">
	private ${col.javaType} ${col.attrName};	//${col.columnComment}
	</#if>
	</#list>
	
	public ${table.className}() {
		super();
	}
	
	<#list table.columnList as col>
	<#if col.columnName != "id" && col.columnName != "create_date" && col.columnName != "update_date">
    
    <#if col.javaType == "Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	</#if>
    public ${col.javaType} ${col.getMethod}() {
    	return ${col.attrName};
    }
    public void ${col.setMethod}(${col.javaType} ${col.attrName}) {
    	this.${col.attrName} = ${col.attrName};
    }
    </#if>
	</#list>
	
}