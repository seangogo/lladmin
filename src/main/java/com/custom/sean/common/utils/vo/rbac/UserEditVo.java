package com.custom.sean.common.utils.vo.rbac;

import com.custom.sean.common.utils.menu.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import java.util.Set;

/**
 *
 * @author sean
 * @date 2018/3/14
 */
@Getter
@Setter
public class UserEditVo {
    /**
     * id
     */
    private Long id;

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
    @Email
    private String email;


    /**
     *  头像url
     */
    private String imgUrl;


    /**
     * 组织编码
     */
    private String orgId;

    /**
     * 角色id
     */
    private Set<Long> roleId;
}
