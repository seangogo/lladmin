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




}
