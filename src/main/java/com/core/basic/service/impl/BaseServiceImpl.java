package com.core.basic.service.impl;

import com.core.basic.dao.BaseDao;
import com.core.basic.entity.BaseEntity;
import com.core.basic.service.IBaseService;
import com.core.page.SysPage;
import com.core.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/7/9
 */
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class BaseServiceImpl<D extends BaseDao<T>,T extends BaseEntity> implements IBaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public T get(Long id) {
        return dao.get(id);
    }

    @Override
    public T getForUpdate(String id) {
        return dao.getForUpdate(id);
    }

    @Override
    public T getBy(String column, String value) {
        return dao.getBy(column, value);
    }

    @Override
    public List<T> fromTable(String sql) {
        return dao.fromTable(sql);
    }

    @Override
    public List<Map<String, Object>> findListSQL(String sql) {
        return dao.findListSQL(sql);
    }

    @Override
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int save(T entity) {
        if(entity.getId() == null){
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            return dao.insert(entity);
        }else{
            entity.setUpdateDate(new Date());
            return dao.update(entity);
        }
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int exec(String sql) {
        return dao.exec(sql);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int delete(T entity) {
        return dao.delete(entity);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void delete(List<T> list) {
        if(list != null && list.size()>0){
            for(T entity : list){
                if(entity != null){
                    dao.delete(entity);
                }
            }
        }
    }

    @Override
    public int getCount(T entity) {
        return dao.getCount(entity);
    }

    @Override
    public SysPage<T> findPage(T entity, HttpServletRequest request) {
        SysPage<T> sysPage = new SysPage<T>();
        if(entity.getFirst() == null){
            entity.setFirst(0);
        }
        if(entity.getFirst() < 0){
            entity.setFirst(0);
        }
        if(entity.getMax() == null){
            entity.setMax(sysPage.getPageSize());
        }
        sysPage.setPageSize(entity.getMax());

        List<T> list = dao.findList(entity);
        Integer count = dao.getCount(entity);

        if(entity.getFirst() < 1){
            sysPage.setPage(0);
        }else{
            sysPage.setPage(Tool.ceil(entity.getFirst(), sysPage.getPageSize()));
        }

        if(entity.getMax() != null && count>0){
            sysPage.setPageTotal(Tool.ceil(count, entity.getMax()));
        }
        if(sysPage.getPage() > 0){
            sysPage.setPrevPage(sysPage.getPage()-1);
        }
        if(sysPage.getPage() < (sysPage.getPageTotal()-1)){
            sysPage.setNextPage(sysPage.getPage()+1);
        }else{
            sysPage.setNextPage(sysPage.getPage());
        }
        sysPage.setList(list);
        sysPage.setCount(count);
        sysPage.setUrl(request.getContextPath() + request.getServletPath());
        return sysPage;
    }
}
