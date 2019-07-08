package com.seangogo.modules.system.service.mapper;

import com.seangogo.base.mapper.EntityMapper;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Mapper(componentModel = "spring", uses = {RoleMapper.class, DeptMapper.class, JobMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
