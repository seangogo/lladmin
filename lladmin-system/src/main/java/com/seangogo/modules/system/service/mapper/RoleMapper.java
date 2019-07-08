package com.seangogo.modules.system.service.mapper;


import com.seangogo.base.mapper.EntityMapper;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Mapper(componentModel = "spring", uses = {ResourceMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
