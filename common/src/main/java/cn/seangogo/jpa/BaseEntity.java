package cn.seangogo.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * 实体基类
 * @author sean
 * @param <U> 用户ID
 */
@MappedSuperclass
@EqualsAndHashCode(of = "id")
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U> implements Serializable {
    /**
     * 主键ID自动生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
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
    @Column(name = "update_by",length = 36)
    protected U updateBy;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @Column(name = "created_dt",updatable = false)
    protected Timestamp createdDate;
    /**
     * 修改时间
     */
    @UpdateTimestamp
    @Column(name = "updated_dt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    protected Timestamp updatedDate;

    /**
     * 删除标志 0 - 正常 1 - 删除
     */
    @Column(name = "del_flag")
    @JsonIgnore
    private Integer delFlag=0;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    @JsonIgnore
    protected Integer version;

    public @interface Update {}
}