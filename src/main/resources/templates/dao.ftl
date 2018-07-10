package ${packageName}.dao;

import org.apache.ibatis.annotations.Mapper;
import ${packageName}.entity.${table.className};
import ${corePackagePath}.basic.dao.BaseDao;


@Mapper
public interface ${table.className}Dao extends BaseDao<${table.className}>{
	
}
