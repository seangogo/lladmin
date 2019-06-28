package com.custom.sean.common.domain;

import com.custom.sean.common.utils.basics.TimeUtils;
import com.custom.sean.common.utils.jpa.BaseEntity;
import com.custom.sean.common.utils.menu.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/2
 */
@Entity
@Table(name = "auth_user")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "del_flg = 0")
public class User extends BaseEntity<String> implements UserDetails {

    /**
     * 用户姓名
     */
    private String realName;

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
     *  头像url
     */
    private String imgUrl;


    /**
     * 嵌入类
     */
    @Embedded
    private Account account = new Account();

    /**
     * 用户的所有角色
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<RoleUser> roles = new HashSet<>();


    /**
     * 用户所在的组织
     */
    @ManyToOne
    private Org org;

    /**
     * 品牌集合
     */
    @Transient
    @JsonIgnore
    private Set<String> brandCodes;

    /**
     * 项目集合
     */
    @Transient
    @JsonIgnore
    private Set<String> projectCodes;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<RoleUser> roleUsers = this.getRoles();
        if (roleUsers != null) {
            for (RoleUser roleUser : roleUsers) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleUser.getRole().getCode());
                authorities.add(authority);
                Set<RoleResource> roleResources = roleUser.getRole().getResources();
                roleResources.forEach(roleResource->authorities.add(new SimpleGrantedAuthority(roleResource.getResource().getCode())));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    /**
     * @return 是否激活
     */
    @Override
    public boolean isAccountNonExpired() {
        return account.isActive();
    }

    /**
     * @return 账户是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return !account.isLocked();
    }

    /**
     * @return 账户是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return TimeUtils.UDateToLocalDate(account.getExpireTime()).isAfter(LocalDate.now());
    }

    /**
     * @return 账户是否删除
     */
    @Override
    public boolean isEnabled() {
        return this.getDelFlag().equals(0) && this.account != null;
    }
}