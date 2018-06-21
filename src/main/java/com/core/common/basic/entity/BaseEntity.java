package com.core.common.basic.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date updateDate;
	private Date createDate;
	private Integer first;
	private Integer max;
	private String orderBy;
	
	private Date updateDateStart;
	private Date updateDateEnd;
	private Date createDateStart;
	private Date createDateEnd;
	
	/**
	 * 当前实体分页对象
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDateStart() {
		return updateDateStart;
	}

	public void setUpdateDateStart(Date updateDateStart) {
		this.updateDateStart = updateDateStart;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDateEnd() {
		return updateDateEnd;
	}

	public void setUpdateDateEnd(Date updateDateEnd) {
		this.updateDateEnd = updateDateEnd;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	
}
