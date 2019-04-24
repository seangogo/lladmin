package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.domain.Dept;
import cn.seangogo.modules.system.service.dto.DeptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-25
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends EntityMapper<DeptDTO, Dept> {

}