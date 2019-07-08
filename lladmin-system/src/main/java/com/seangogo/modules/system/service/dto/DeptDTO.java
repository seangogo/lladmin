package com.seangogo.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Data
public class DeptDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    private Long pid;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDTO> children;

    private Timestamp createTime;

    public String getLabel() {
        return name;
    }
}