package com.custom.sean.common.utils.vo.rbac;

import com.custom.sean.common.utils.menu.OrgType;
import com.custom.sean.common.utils.menu.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author sean
 * @date 2018/2/5
 */
@Getter
@Setter
public class UserDetailsVo {

    /**
     * 账户名
     */
    private String username;

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
    private String email;

    /**
     *  头像url
     */
    private String imgUrl;

    /**
     * 层级编码
     */
    private String orgCode;

    /**
     * 创建人 登录帐号
     */
    private String createdBy;


    /**
     * 创建时间
     */
    @Temporal(TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date createdDate;

    /**
     * 机构类别
     */
    private OrgType orgType;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织简介
     */
    private String remark;

    /**
     * 角色
     */
    private List<UserDetailRoleVo> roles;
}
