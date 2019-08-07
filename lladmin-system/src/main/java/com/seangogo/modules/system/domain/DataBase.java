package com.seangogo.modules.system.domain;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 数据库
 *
 * @author seang
 * @date 2019/8/7 9:39
 */
@Entity
@Getter
@Setter
@Table(name = "generator_database")
public class DataBase extends BaseEntity<String> {

    private String alias;

    private String name;

    private String jdbcUrl;

    private String username;

    @JsonIgnore
    private String password;


}
