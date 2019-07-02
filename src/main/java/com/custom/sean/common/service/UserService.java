package com.custom.sean.common.service;

import com.custom.sean.common.domain.Role;
import com.custom.sean.common.domain.User;
import com.custom.sean.common.utils.jpa.BaseService;
import com.custom.sean.common.utils.vo.DataResult;
import com.custom.sean.common.utils.vo.PageResult;
import com.custom.sean.common.utils.vo.StateResult;
import com.custom.sean.common.utils.vo.rbac.AccountOut;
import com.custom.sean.common.utils.vo.rbac.UserDetailsVo;
import com.custom.sean.common.utils.vo.rbac.UserEditVo;
import com.custom.sean.common.utils.vo.rbac.UserSaveVo;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


/**
 * @author sean
 * 2017/11/2.
 */

public interface UserService extends BaseService<User, Long>{

    /**
     * 获取当前用户信息,查询该用户所有菜单按钮
     *
     * @return AccountOut
     */

    AccountOut getMeInfo();

    /**
     * 账户名是否可用
     *
     * @param userName 帐户名
     * @return 是否可用
     */
     boolean isUser(String userName);

    /**
     * 添加用户
     *
     * @param userSaveVo 前端数据
     */
     void createUser(UserSaveVo userSaveVo, String orgCode);

    /**
     * 建立角色账户关联
     *
     * @param user 包含角色ID的试图
     * @param role 账户实体
     */
     void createRoleAdmin(User user, Role role);

    /**
     * 修改用户
     *
     * @param userSaveVo 前端数据
     */
    void updateUser(Long id, UserSaveVo userSaveVo, String orgCode);


    /**
     * 删除用户
     *
     * @param id      用户id
     * @param orgCode 组织编码
     */
    void deleteUser(Long id, String orgCode);

    /**
     * 批量删除
     *
     * @param id      用户ids
     * @param orgCode 组织编码
     */
    void batchesDelete(String id, String orgCode);

    /**
     * 根据项目code查询所有用户
     *
     * @param code 项目code
     * @return 组织下所有的labels
     */
     List<Map<String, String>> findByOrgCode(String code);

    /**
     * 用户详情
     *
     * @param id 用户主键
     * @return UserDetailsVo
     */
    UserDetailsVo findUserDetail(Long id);

    /**
     *  根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User findUserByLoginName(String username);

    UserEditVo findUserEdit(Long id, String username);

    /**
     * 系统内激活
     *
     * @param id       用戶id
     * @param password 密码
     * @return
     */
    StateResult activeAccount(Long id, String password);

    /**
     * 锁定/解锁
     *
     * @param id 用户id
     */
    void locked(Long id);
    /**
     * 退出登陆
     *
     * @return success
     */
    StateResult logout();

    /**
     * 修改密码
     *
     * @param username    账户名
     * @param map  oldPassword 旧密码  password 新密码
     */
    void modifyPassword(String username, Map<String,String> map);

    /**
     * 用户动态分页
     *
     * @param org      当前账户所属组织
     * @param realName 姓名
     * @param email    邮箱
     * @param phone    电话
     * @param username 账户
     * @param orgCode  组织编码
     * @param roleId   角色
     */
    PageResult page(Pageable pageable, String org, String realName, String email, String phone, String username, String orgCode, String roleId);

    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param userName 账户名
     */
    StateResult sendCode(String phone, String userName);

    /**
     * 重置密码
     *
     * @param userName 用户名
     * @param password 密码
     * @param phone    手机号
     * @param code     验证码
     */
    DataResult resetPassword(String userName, String password, String phone, String code);

    void updateExpireTime(User user);
}
