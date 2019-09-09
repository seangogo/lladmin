package com.seangogo.modules.system.service.dto;

import java.util.Date;
import java.util.Set;

/**
 * @author seang
 * @date 2019/8/8 15:15
 */
public interface TableInfoDto {
    public Long getId();

    public String getTableName();

    public Date getCreateTime();

    public String getEngine();

    public String getCoding();

    public String getRemark();

    public String getCreateSql();

    public Set<FieldInfoDto> getFields();
}
