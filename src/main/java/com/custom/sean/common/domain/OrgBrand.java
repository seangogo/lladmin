package com.custom.sean.common.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "auth_org_brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgBrand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "id", strategy = "uuid2")
    @GeneratedValue(generator = "id")
    private String id;
    /**
     * 组织
     */
    @ManyToOne
    private Org org;
    /**
     * 角色
     */
    @ManyToOne
    private Brand brand;

    public OrgBrand(Org org, Brand brand) {
        this.org = org;
        this.brand = brand;
    }
}
