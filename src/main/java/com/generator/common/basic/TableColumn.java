package com.generator.common.basic;


public class TableColumn {

	private String columnName;		// 字段名
	private String dataType;		// 字段类型
	private String columnType;		// 字段类型（含长度）
	private String isNullable;		// 是否可空
	private String columnComment;	// 字段注解
	private String rank;			// 排序


	private String attrName;		// 属性名
	private String javaType;		// Java类型名称
	private String getMethod;		// get方法名
	private String setMethod;		// set方法名
	
	public TableColumn() {
		super();
	}
	
	public TableColumn(String attrName, String columnName, String dataType,
					   String columnType, String isNullable, String columnComment,
					   String rank) {
		super();
		this.attrName = attrName;
		this.columnName = columnName;
		this.dataType = dataType;
		this.columnType = columnType;
		this.isNullable = isNullable;
		this.columnComment = columnComment;
		this.rank = rank;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getGetMethod() {
		return getMethod;
	}
	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}
	public String getSetMethod() {
		return setMethod;
	}
	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	@Override
	public String toString() {
		return "SysColumn [attrName=" + attrName + ", columnName=" + columnName
				+ ", dataType=" + dataType + ", columnType=" + columnType
				+ ", isNullable=" + isNullable + ", columnComment="
				+ columnComment + ", rank=" + rank + ", javaType=" + javaType
				+ ", getMethod=" + getMethod + ", setMethod=" + setMethod + "]";
	}
	
	
	
}
