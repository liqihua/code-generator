<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>${table.tableNameCH}添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/${table.lowerName}WebController/list">${table.tableNameCH}列表</a></li>
			<li class="active"><a href="/sys/${table.lowerName}WebController/form">${table.tableNameCH}添加</a></li>
		</ul>
		<sys:message content="${"$"}{message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/sys/${table.lowerName}WebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<#list table.columnList as col>
			<#if col.columnName != "id" && col.columnName != "create_date" && col.columnName != "update_date">
			<div class="control-group">
				<label class="control-label">${col.columnComment}：</label>
				<div class="controls">
				<#if col.javaType == "Date">
					<input name="${col.attrName}" type="text" maxlength="20" class="input-medium Wdate <#if col.isNullable == "NO">required</#if>" readOnly="true" value="<fmt:formatDate value="${"$"}{entity.${col.attrName}}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				<#else>
					<#if col.attrName == "provinceId">
					<form:select path="provinceId" class="input-large <#if col.isNullable == "NO">required</#if>" onchange="provinceChange()">
						<form:option value="" label=""/>
						<form:options items="${"$"}{fnc:getAllProvince()}"   htmlEscape="false"/>
					</form:select>
					</#if>
					<#if col.attrName == "cityId">
					<form:select path="cityId" class="input-large <#if col.isNullable == "NO">required</#if>" onchange="cityChange()">
						<form:option value="" label=""/>
					</form:select>
					</#if>
					<#if col.attrName == "districtId">
					<form:select path="districtId" class="input-large <#if col.isNullable == "NO">required</#if>">
						<form:option value="" label=""/>
					</form:select>
					</#if>
					<#if col.attrName != "provinceId" && col.attrName != "cityId" && col.attrName != "districtId">
					<form:input path="${col.attrName}" htmlEscape="false" class="input-xlarge <#if col.isNullable == "NO">required</#if>"/>
					</#if>
				</#if>
				</div>
			</div>
			</#if>
			</#list>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
