package com.seangogo.modules.nft.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * NFP平台
 */
@Entity
@Table(name = "nft_platform")
@Getter
@Setter
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 平台名称
     */
    private String name;

    /**
     * 平台官网
     */
    private String url;

    /**
     * 微信公众号名称
     */
    private String wechatName;

    /**
     * 开启二级市场
     */
    private Boolean business;

    /**
     * 平台备注
     */
    private String remark;

    public void copy(Platform source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
