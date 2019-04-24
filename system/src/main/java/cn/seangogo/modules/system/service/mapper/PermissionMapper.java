package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.modules.system.domain.Permission;
import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

}
