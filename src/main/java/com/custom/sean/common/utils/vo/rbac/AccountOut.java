package com.custom.sean.common.utils.vo.rbac;

import com.custom.sean.common.utils.menu.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sean
 * @date 2017/11/6
 */
@Setter
@Getter
public class AccountOut {
    /**
     * 账户名
     */
    private String userName;

    /**
     * 真实姓名
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
     * 头像url
     */
    private String imgUrl;

    /**
     * 菜单
     */
    private AuthInfo authInfo;

    /**
     * 按钮
     */
    private Set<String> buttons;
    /**
     * 数字字典
     */
    private Map<String, List<Map<String, String>>> dic;
}
