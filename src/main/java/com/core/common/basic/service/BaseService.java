package com.core.common.basic.service;

import com.core.common.basic.dao.BaseDao;
import com.core.common.basic.entity.BaseEntity;
import com.core.common.page.SysPage;
import com.core.common.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * cglib模式
 * @param <D>
 * @param <T>
 */
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseEntity> {
	
	@Autowired
	protected D dao;
	
	public T get(String id){
		return dao.get(id);
	}

	public T getForUpdate(String id){
		return dao.getForUpdate(id);
	}

	public T getBy(String column,String value){
		return dao.getBy(column, value);
	}
	
	public List<T> fromTable(String sql){
		return dao.fromTable(sql);
	}
	
	public List<Map<String,Object>> findListSQL(String sql){
		return dao.findListSQL(sql);
	}
	
	public List<T> findList(T entity){
		return dao.findList(entity);
	}


	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int save(T entity){
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
	public int exec(String sql){
		return dao.exec(sql);
	}


	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delete(T entity){
		return dao.delete(entity);
	}
	
	
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(List<T> list){
		if(list != null && list.size()>0){
			for(T entity : list){
				if(entity != null){
					dao.delete(entity);
				}
			}
		}
	}
	
	public int getCount(T entity){
		return dao.getCount(entity);
	}
	
	public SysPage<T> findPage(T entity, HttpServletRequest request){
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
