package ${packageName}.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${corePackagePath}.basic.service.impl.BaseServiceImpl;

import ${packageName}.entity.dto.DTO${table.shortName};
import ${packageName}.entity.${table.className};
import ${packageName}.dao.${table.className}Dao;
import ${packageName}.service.${table.className}Service;




@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}Dao,${table.className}> implements ${table.className}Service {

	@Override
	public DTO${table.shortName} getDTO(${table.className} entity){
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

	@Override
	public List<DTO${table.shortName}> getDTOList(List<${table.className}> entityList){
    	List<DTO${table.shortName}> list = new ArrayList<DTO${table.shortName}>();
        if(entityList != null && entityList.size()>0){
        	for(${table.className} entity : entityList){
        		list.add(getDTO(entity));
        	}
        }
        return list;
    }

}