package com.custom.sean.common.controller;

import com.custom.sean.common.annotation.SystemControllerLog;
import com.custom.sean.common.domain.User;
import com.custom.sean.common.service.UserService;
import com.custom.sean.common.utils.basics.JwtTokenUtil;
import com.custom.sean.common.utils.vo.DataResult;
import com.custom.sean.common.utils.vo.PageResult;
import com.custom.sean.common.utils.vo.StateResult;
import com.custom.sean.common.utils.vo.rbac.UserDetailsVo;
import com.custom.sean.common.utils.vo.rbac.UserEditVo;
import com.custom.sean.common.utils.vo.rbac.UserSaveVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户模块
 * @author sean
 * 2017/11/2.
 */
@RestController
@RequestMapping("auth/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 分页动态查询
     *
     * @param pageable 分页
     * @param realName 姓名
     * @param email    邮箱
     * @param phone    电话
     * @param username 账户
     * @param orgCode  组织
     * @return         分页
     */
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('user')")
    public PageResult<User> searchPage(@PageableDefault(sort = "createdDate", direction = Direction.DESC) Pageable pageable,
                                       @RequestParam(required = false) String realName,
                                       @RequestParam(required = false) String email,
                                       @RequestParam(required = false) String phone,
                                       @RequestParam(required = false) String username,
                                       @RequestParam(required = false) String orgCode,
                                       @RequestParam(required = false) String roleId){
      return userService.page(pageable, jwtTokenUtil.getOrgCode(), realName, email, phone, username, orgCode, roleId);
    }

    /**
     * 账户名是否存在
     *
     * @param userName 账户名
     * @return true 可用 false 不可用
     */
    @GetMapping("/isUse/{userName}")
    @PreAuthorize("hasAuthority('user-opt')")
    public boolean isUse(@PathVariable String userName) {
        return userService.isUser(userName);
    }

    /**
     * 新增
     *
     * @param userSaveVo vo
     * @return success
     */
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('user-opt')")
    @SystemControllerLog(description = "新建人员")
    public StateResult add(@RequestBody UserSaveVo userSaveVo){
        String orgCode = jwtTokenUtil.getOrgCode();
        userService.createUser(userSaveVo, orgCode);
        return StateResult.success();
    }

    /**
     * 删除
     *
     * @param id      主鍵
     * @return success
     */
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('user-opt')")
    @SystemControllerLog(description = "人员删除")
    public StateResult delete(@PathVariable Long id){
        String orgCode = jwtTokenUtil.getOrgCode();
        userService.deleteUser(id, orgCode);
        return StateResult.success();
    }

    /**
     * 修改
     * @param id 用户id
     * @param userSaveVo vo
     * @return success
     */
    @PutMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('user-opt')")
    @SystemControllerLog(description = "人员信息修改")
    public StateResult edit(@PathVariable Long id,
                            @RequestBody UserSaveVo userSaveVo){
        String orgCode = jwtTokenUtil.getOrgCode();
        userService.updateUser(id, userSaveVo, orgCode);
        return StateResult.success();
    }

    /**
     * 用户批量删除
     *
     * @param id      用户id
     * @return success
     */
    @DeleteMapping(value = "/batchesRemove/{id}")
    @PreAuthorize("hasAuthority('user-opt')")
    @SystemControllerLog(description = "人员批量删除")
    public StateResult batchesRemove(@PathVariable String id){
        String orgCode = jwtTokenUtil.getOrgCode();
        userService.batchesDelete(id, orgCode);
        return StateResult.success();
    }

    /**
     * 激活账号
     *
     * @param id  用户id
     * @param map 参数集合
     * @return success
     */
    @PutMapping(value = "/active/{id}")
    @PreAuthorize("hasAuthority('user-opt')")
    @SystemControllerLog(description = "系统内激活")
    public StateResult editActiveAccount(@PathVariable(value = "id") Long id,
                                         @RequestBody Map<String, String> map) {
        return userService.activeAccount(id, map.get("password"));
    }

    /**
     * 锁定账户
     *
     * @param id 用户id
     * @return success
     */
    @PutMapping("/lock/{id}")
    @PreAuthorize("hasAuthority('user-opt')")
    @SystemControllerLog(description = "锁定/解锁")
    public StateResult lock(@PathVariable(value = "id") Long id) {
        userService.locked(id);
        return StateResult.success();
    }


    /**
     * 回显数据（修改）
     *
     * @param id 用户id
     * @return success
     */
    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('user-opt')")
    public UserEditVo editData(@PathVariable Long id) {
        String username=jwtTokenUtil.getUserName();
        return userService.findUserEdit(id,username);
    }

    /**
     * 用户详情
     *
     * @param id 用户id
     * @return 用户详情Vo
     */
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('user')")
    public UserDetailsVo searchUser(@PathVariable Long id){
        return userService.findUserDetail(id);
    }

    /**
     * 退出登陆
     *
     * @return success
     */
    @GetMapping(value = "/logout")
    @SystemControllerLog(description = "退出登陆")
    public StateResult logout() {
        return userService.logout();
    }

    /**
     * 修改密码
     * @param map 参数
     * @return success
     */
    @PutMapping("password/modify")
    @PreAuthorize("isAuthenticated()")
    @SystemControllerLog(description = "修改密码")
    public StateResult editPassword(@RequestBody Map<String, String> map) {
        String username = jwtTokenUtil.getUserName();
        userService.modifyPassword(username,map);
        return StateResult.success();
    }

    /**
     * 重置密码
     *
     * @param map 参数（账户，密码，手机号，验证码）
     * @return success
     */
    @PutMapping("/resetPassword")
    @SystemControllerLog(description = "找回密码")
    public DataResult resetPassword(@RequestBody Map<String, String> map) {
        return userService.resetPassword(map.get("username"), map.get("password"), map.get("phone"), map.get("captcha"));
    }

    /**
     * 获取当前账户登录的用户信息
     * @return 菜单 按钮权限
     */
    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public DataResult authenticationInfo() {
        return DataResult.success(userService.getMeInfo());
    }


}
