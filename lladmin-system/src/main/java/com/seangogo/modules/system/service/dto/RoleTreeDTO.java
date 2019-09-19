package com.seangogo.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author seang
 * @date 2019/7/19 16:03
 */
@Getter
@Setter
@ToString
public class RoleTreeDTO {
    /** value */
    @JsonProperty("value")
    private Long id;

    /**key */
    @JsonProperty("title")
    private String name;


    /** parent */
    private Long parentId;

    private String levelCode;

    private String remark;

    private String code;

    /**
     * 子节点
     */
    private List<RoleTreeDTO> children;
}
