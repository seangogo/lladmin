package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.domain.AlipayConfig;
import cn.seangogo.modules.system.service.dto.AlipayConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-23
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlipayConfigMapper extends EntityMapper<AlipayConfigDTO, AlipayConfig> {

}