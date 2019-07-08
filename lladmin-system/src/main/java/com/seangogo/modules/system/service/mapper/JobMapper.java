package com.seangogo.modules.system.service.mapper;

import com.seangogo.base.mapper.EntityMapper;
import com.seangogo.modules.system.domain.Job;
import com.seangogo.modules.system.service.dto.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Mapper(componentModel = "spring", uses = {DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends EntityMapper<JobDTO, Job> {

    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName")
    JobDTO toDto(Job job, String deptSuperiorName);
}