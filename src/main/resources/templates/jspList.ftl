<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>${table.tableNameCH}列表</title>
	    <%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
	<body>
    	<ul class="nav nav-tabs sys-font">
			<li class="active"><a href="/sys/${table.lowerName}WebController/list">${table.tableNameCH}列表</a></li>
			<li><a href="/sys/${table.lowerName}WebController/form">${table.tableNameCH}添加</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="${table.lowerName}" action="/sys/${table.lowerName}WebController/list" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<#list table.columnList as col>
				<#if col.columnName != "id" && col.columnName != "create_date" && col.columnName != "update_date">
				<li><label>${col.columnComment}：</label>
					<form:input path="${col.attrName}" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				</#if>
				</#list>
				<li><label>创建时间：</label>
					<input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${"$"}{${table.lowerName}.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
					<input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${"$"}{${table.lowerName}.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</li>
				<li><label>更新时间：</label>
					<input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${"$"}{${table.lowerName}.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
					<input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${"$"}{${table.lowerName}.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
				<li class="clear"></li>
			</ul>
		</form:form>
		<sys:message content="${"$"}{message }"/>
    	<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
		<thead class="sys-font">
			<tr>
				<#list table.columnList as col>
				<#if col.columnName != "id">
				<th>${col.columnComment}</th>
				</#if>
				</#list>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${"$"}{page.list}" var="entity">
			<tr>
				<#list table.columnList as col>
				<#if col.columnName != "id">
				<#if col.javaType == "Date">
				<td><fmt:formatDate value="${"$"}{entity.${col.attrName}}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<#else>
				<td>${"$"}{entity.${col.attrName} }</td>
				</#if>
				</#if>
				</#list>
				<td>
    				<a href="/sys/${table.lowerName}WebController/form?id=${"$"}{entity.id }">修改</a>
					<a href="/sys/${table.lowerName}WebController/delete?id=${"$"}{entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<sys:page page="${"$"}{page }"/>
  	</body>
</html>
