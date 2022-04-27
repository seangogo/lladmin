package com.seangogo.modules.nft.service.mapper;

import com.seangogo.base.mapper.EntityMapper;
import com.seangogo.modules.nft.domain.Platform;
import com.seangogo.modules.nft.service.dto.PlatformDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author xiaojun
* @date 2022-04-27
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlatformMapper extends EntityMapper<PlatformDTO, Platform> {

}