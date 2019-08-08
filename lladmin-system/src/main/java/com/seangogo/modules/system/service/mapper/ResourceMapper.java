package com.seangogo.modules.system.service.mapper;

import com.seangogo.base.mapper.EntityMapper;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.service.dto.ResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * DTO dean
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper extends EntityMapper<ResourceDTO, Resource> {

}
