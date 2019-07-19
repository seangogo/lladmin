package com.seangogo.base.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * 实体基类
 *
 * @param <U> 用户ID
 * @author sean
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID自动生成策略
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 创建人 登录帐号
     */
    @CreatedBy
    @Column(name = "create_by")
    protected U createdBy;

    /**
     * 修改人 登录的帐号
     */
    @LastModifiedBy
    @Column(name = "update_by", length = 36)
    protected U updateBy;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "created_date")
    protected Timestamp createdTime;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "updated_time")
    protected Timestamp updatedTime;

    /**
     * 删除标志 0 - 正常 1 - 删除
     */
    @Column(name = "del_flg")
    @JsonIgnore
    private boolean delFlag;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    @JsonIgnore
    protected Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity entity = (BaseEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}