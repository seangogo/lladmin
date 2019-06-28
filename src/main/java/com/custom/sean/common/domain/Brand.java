package com.custom.sean.common.domain;

import com.custom.sean.common.utils.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 品牌
 * @author sean
 * @date 2018/2/6
 */
@Entity
@Table(name = "auth_brand")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "del_flg = 0")
public class Brand extends BaseEntity<String> {

    /**
     * 品牌编码
     */
    @Column(length = 20, unique = true,updatable = false)
    private String code;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logo;


    /**
     * 品牌关联的所有组织
     */
    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();

    /**
     * 品牌详情
     */
    private String remark;
}
