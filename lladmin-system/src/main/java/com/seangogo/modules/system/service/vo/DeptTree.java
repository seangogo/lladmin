package com.seangogo.modules.system.service.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
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
    private Long pid;

    /** children */
    private List<DeptTree> children;

    @Override
    public Long getId(){
        return id;
    }

    @Override
    public Long getPid(){
        return pid;
    }

    @Override
    public String getName(){
        return name;
    }

    public DeptTree(Long id, String name, Long pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public static DeptTree toTree(List<DeptTreeInterface> list, DeptTreeInterface parent) {
        DeptTree dept = new DeptTree(parent.getId(),parent.getName(),parent.getPid());
        List<DeptTree> children = new ArrayList<>();
        for (DeptTreeInterface tree : list) {
            if (tree.getPid().equals(parent.getId())) {
                children.add(toTree(list, tree));
            }
        }
        dept.setChildren(children);
        return dept;
    }
}
