package com.seangogo.modules.system.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seangogo.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

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
@NamedEntityGraph(
        name = "database-table-field",
        attributeNodes = @NamedAttributeNode(value = "tables", subgraph = "table-subgraph"),
        subgraphs = {
                @NamedSubgraph(
                        name = "table-subgraph",
                        attributeNodes = @NamedAttributeNode("fields")
                )
        }
)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class DataBase extends BaseEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String alias;

    private String name;

    private String jdbcUrl;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "dataBase",cascade=CascadeType.REMOVE)
    private Set<TableInfo> tables;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataBase)) {
            return false;
        }
        return super.getId() != null && super.getId().equals(((DataBase) obj).getId());
    }

    @Override
    public int hashCode() {
        return 2022;
    }
}
