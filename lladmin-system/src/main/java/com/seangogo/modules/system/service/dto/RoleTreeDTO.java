package com.seangogo.modules.system.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seang
 * @date 2019/7/19 16:03
 */
@Getter
@Setter
public class RoleTreeDTO {
    /**
     * 子节点
     */
    private List<RoleTreeDTO> children = new ArrayList<>();
}