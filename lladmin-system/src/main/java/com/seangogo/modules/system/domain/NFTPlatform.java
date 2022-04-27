package com.seangogo.modules.system.domain;

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
public class NFTPlatform {

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
     * 平台备注
     */
    private String remark;

}
