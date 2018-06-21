package com.generator.common.basic;

import java.util.List;

public class Table {
	private String shortName;		// Java类名，去掉表前缀
	private String tableName;		// 表名
	private String tableNameCH;		// 表名（中文）
	private String className;		// Java类名
	private String lowerName;		// 类名首字母小写
	private List<TableColumn> columnList;	// 字段
	
	private String permView;		//shiro查询权限符
	private String permEdit;		//shiro编辑权限符
	private String permDelete;		//shiro删除权限符
	
	public Table() {
		super();
	}
	
	public Table(String tableName, String tableNameCH, String className, String lowerName, List<TableColumn> columnList) {
		super();
		this.tableName = tableName;
		this.tableNameCH = tableNameCH;
		this.className = className;
		this.lowerName = lowerName;
		this.columnList = columnList;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<TableColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<TableColumn> columnList) {
		this.columnList = columnList;
	}
	public String getLowerName() {
		return lowerName;
	}
	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public String getTableNameCH() {
		return tableNameCH;
	}

	public void setTableNameCH(String tableNameCH) {
		this.tableNameCH = tableNameCH;
	}

	public String getPermView() {
		return permView;
	}

	public void setPermView(String permView) {
		this.permView = permView;
	}

	public String getPermEdit() {
		return permEdit;
	}

	public void setPermEdit(String permEdit) {
		this.permEdit = permEdit;
	}

	public String getPermDelete() {
		return permDelete;
	}

	public void setPermDelete(String permDelete) {
		this.permDelete = permDelete;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	
}
