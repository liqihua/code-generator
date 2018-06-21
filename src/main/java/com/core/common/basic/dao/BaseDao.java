package com.core.common.basic.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface BaseDao<T> {
	
	public T get(String id);

	public T getForUpdate(String id);

	public T getBy(@Param("column") String column, @Param("value") String value);
	
	public List<T> fromTable(@Param("sql") String sql);
	
	public List<Map<String,Object>> findListSQL(@Param("sql") String sql);
	
	public List<T> findList(T entity);
	
	public int insert(T entity);
	
	public int update(T entity);
	
	public int exec(@Param("sql") String sql);
	
	public int delete(T entity);
	
	public int getCount(T entity);
	
}
