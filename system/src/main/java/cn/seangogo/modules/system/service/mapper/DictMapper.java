package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.domain.Dict;
import cn.seangogo.modules.system.service.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends EntityMapper<DictDTO, Dict> {

}