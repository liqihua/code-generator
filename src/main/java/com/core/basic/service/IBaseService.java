package com.core.basic.service;

import com.core.page.SysPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/7/9
 */

public interface IBaseService<T> {

    public T get(Long id);

    public T getForUpdate(String id);

    public T getBy(String column, String value);

    public List<T> fromTable(String sql);

    public List<Map<String,Object>> findListSQL(String sql);

    public List<T> findList(T entity);

    public int save(T entity);

    public int exec(String sql);

    public int delete(T entity);

    public void delete(List<T> list);

    public int getCount(T entity);

    public SysPage<T> findPage(T entity, HttpServletRequest request);


}
