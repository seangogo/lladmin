package com.custom.sean.common.domain;

import com.custom.sean.common.utils.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "auth_project")
@Setter
@Getter
@DynamicUpdate
@Where(clause = "del_flg = 0")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "ProjectInfo.detail",
                attributeNodes = {//attributeNodes 来定义需要懒加载的属性
                        @NamedAttributeNode("brand")
                })})
public class Project extends BaseEntity<String> {
    public interface ProjectSimpleView {}
    /**
     * 项目标题
     */
    @JsonView(ProjectSimpleView.class)
    @NotNull
    private String name;

    /**
     * 项目代号
     */
    @JsonView(ProjectSimpleView.class)
    @NotNull
    @Column(unique = true, updatable = false)
    private String code;

    /**
     * 角色
     */
    @ManyToOne
    private Brand brand;

    /**
     * 项目描述
     */
    private String remark;

}
