package cn.seangogo.modules.system.domain;

import cn.seangogo.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author jie
 * @date 2018-11-22
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
@Where(clause = "del_flag = 0")
public class User extends BaseEntity<Long> {
    /**
     * 登陆名
     */
    @NotBlank
    @Column(unique = true)
    private String username;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 false = 女  true = 男
     */
    private boolean sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    @NotBlank
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}", message = "格式错误")
    private String email;

    /**
     * 手机
     */
    @NotBlank
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后修改密码时间
     */
    private Date lastPasswordResetTime;

    /**
     * 激活
     */
    @NotNull
    private Boolean enabled;

    /**
     * 锁定
     */
    private boolean locked;

    /**
     * 过期时间 3年
     */
    @Temporal(TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireTime = new Date(System.currentTimeMillis() + 86400000000L);

    /**
     * 账户锁定次数，最多5次进入锁定
     */
    private int accountLockCount = 0;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

}