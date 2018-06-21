package ${packageName}.dao;

import org.apache.ibatis.annotations.Mapper;
import ${packageName}.entity.${table.className};
import com.core.common.basic.dao.BaseDao;


@Mapper
public interface ${table.className}Dao extends BaseDao<${table.className}>{
	
}
