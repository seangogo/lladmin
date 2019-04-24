package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.modules.system.domain.Menu;
import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jie
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

}
