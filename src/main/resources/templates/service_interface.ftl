package ${packageName}.service;

import ${packageName}.entity.dto.DTO${table.shortName};
import ${packageName}.entity.${table.className};
import ${corePackagePath}.basic.service.IBaseService;

import java.util.List;

public interface ${table.className}Service extends IBaseService<${table.className}> {

    public DTO${table.shortName} getDTO(${table.className} entity);

    public List<DTO${table.shortName}> getDTOList(List<${table.className}> entityList);

}