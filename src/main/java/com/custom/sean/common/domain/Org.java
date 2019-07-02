package com.custom.sean.common.domain;

import com.custom.sean.common.utils.jpa.BaseEntity;
import com.custom.sean.common.utils.menu.OrgType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 组织
 * Created by sean on 2018/1/8.
 */
@Entity
@Getter
@Setter
@Table(name = "auth_org")
public class Org extends BaseEntity<String> {
    /**
     * 名称
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 编码
     */
    @Column(length = 20, unique = true,updatable = false)
    private String code;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 层级编码
     */
    @Column(unique = true)
    private String levelCode;

    /**
     * 机构类别
     */
    private OrgType orgType;

    /**
     * 组织的品牌集合
     */
    @OneToMany(mappedBy = "org", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrgBrand> brands = new HashSet();


    /**
     * 备注
     */
    @Column(name = "remark", length = 100)
    private String remark;


    @Transient
    private List<Org> children;


    @Transient
    private String dealer;

    @Transient
    private String brandIds;

}
