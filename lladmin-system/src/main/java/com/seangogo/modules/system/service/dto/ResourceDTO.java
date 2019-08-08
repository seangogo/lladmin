package com.seangogo.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seangogo.modules.system.domain.enums.ResourceType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Data
@ToString
public class ResourceDTO implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String levelCode;

    private String icon;

    private ResourceType type;

    private String remark;

    private Long parentId;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createdDate;

    private List<ResourceDTO> children;

}
