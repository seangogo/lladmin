package com.custom.sean.common.utils.vo.rbac;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleTree extends BaseTree {

    /**
     * 子节点
     */
    private List<RoleTree> children = new ArrayList<>();
}
