package com.custom.sean.common.utils.vo.rbac;

import com.custom.sean.common.utils.menu.Sex;
import lombok.Getter;
import lombok.Setter;

/**
 * @author seangogo
 */
@Setter
@Getter
public class UserSaveVo {

	/**
	 * 用户名
	 */
	//@Pattern(regexp="^[\\w]{5,12}$",message = "账号输入有误,字符+数字5-12")
	private String username;

	/**
	 * 角色
	 */
	private String roleId;

	/**
	 * 姓名
	 */
	private String realName;

	/**
	 * 性别
	 */
	private Sex sex;

	/**
	 * 手机
	 */
	// @Phone
	private String phone;

	/**
	 * 邮箱
	 */
	// @Email
	private String email;

	/**
	 * 头像url
	 */

	private String imgUrl;


	/**
	 * 层级编码
	 */
	private String orgId;
}
