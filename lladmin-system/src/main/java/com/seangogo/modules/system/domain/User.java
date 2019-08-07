package com.seangogo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import com.seangogo.modules.system.domain.enums.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author sean
 * @date 2017/11/2
 */

@Entity
@Table(name = "system_user")
@Getter
@Setter
@Where(clause = "del_flg = 0")
public class User extends BaseEntity<String> {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 账户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 锁定
     */
    private boolean locked;

    /**
     * 激活
     */
    private boolean active;

    /**
     * 过期时间 3年
     */
    @Temporal(TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireTime = new Date(System.currentTimeMillis() + 86400000000L);

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    @Email
    private String email;


    /**
     * 头像url
     */
    private String avatar;


    /**
     * 用户的所有角色
     */

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "system_users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(super.getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}