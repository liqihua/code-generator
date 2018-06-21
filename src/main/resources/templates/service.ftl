package ${packageName}.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import ${packageName}.entity.${table.className};
import ${packageName}.dao.${table.className}Dao;
import com.core.common.basic.service.BaseService;
import ${packageName}.entity.dto.DTO${table.shortName};

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ${table.className}Service extends BaseService<${table.className}Dao, ${table.className}> {

	
	public DTO${table.shortName} getReturn${table.shortName}(${table.className} entity){
		DTO${table.shortName} ${table.shortName?lower_case} = new DTO${table.shortName}();
		if(entity != null){
			try{
				BeanUtils.copyProperties(entity, ${table.shortName?lower_case});
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ${table.shortName?lower_case};
	}
	
	
	public List<DTO${table.shortName}> getReturn${table.shortName}List(List<${table.className}> entityList){
		List<DTO${table.shortName}> list = new ArrayList<DTO${table.shortName}>();
		if(entityList != null && entityList.size()>0){
			for(${table.className} entity : entityList){
				list.add(getReturn${table.shortName}(entity));
			}
		}
		return list;
	}
	
}