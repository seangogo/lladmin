package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.domain.DictDetail;
import cn.seangogo.modules.system.service.dto.DictDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends EntityMapper<DictDetailDTO, DictDetail> {

}