package com.seangogo.modules.system.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seang
 * @date 2019/9/12 10:11
 */
@Data
public class DeptTree implements DeptTreeInterface {
    /** value */
    @JsonProperty("value")
    private Long id;

    /**key */
    @JsonProperty("title")
    private String name;


    /** parent */
    private Long parentId;

    /** children */
    private List<DeptTree> children;

    @Override
    public Long getId(){
        return id;
    }

    @Override
    public Long getParentId(){
        return parentId;
    }

    @Override
    public String getName(){
        return name;
    }

    public DeptTree(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public static DeptTree toTree(List<DeptTreeInterface> list, DeptTreeInterface parent) {
        DeptTree dept = new DeptTree(parent.getId(),parent.getName(),parent.getParentId());
        List<DeptTree> children = new ArrayList<>();
        for (DeptTreeInterface tree : list) {
            if (parent.getId().equals(tree.getParentId())) {
                children.add(toTree(list, tree));
            }
        }
        dept.setChildren(children);
        return dept;
    }
}
