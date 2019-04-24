package cn.seangogo.modules.system.service.mapper;

import cn.seangogo.mapper.EntityMapper;
import cn.seangogo.modules.system.domain.Job;
import cn.seangogo.modules.system.service.dto.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends EntityMapper<JobDTO, Job> {

}