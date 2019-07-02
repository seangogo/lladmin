package com.custom.sean.common.service.impl;

import com.custom.sean.common.domain.Org;
import com.custom.sean.common.domain.Role;
import com.custom.sean.common.domain.User;
import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.repository.RoleRepository;
import com.custom.sean.common.repository.UserRepository;
import com.custom.sean.common.service.OrgService;
import com.custom.sean.common.service.ResourceService;
import com.custom.sean.common.service.RoleService;
import com.custom.sean.common.service.UserService;
import com.custom.sean.common.utils.basics.JwtTokenUtil;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseServiceImpl;
import com.custom.sean.common.utils.vo.DataResult;
import com.custom.sean.common.utils.vo.PageResult;
import com.custom.sean.common.utils.vo.ResultEnum;
import com.custom.sean.common.utils.vo.StateResult;
import com.custom.sean.common.utils.vo.rbac.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author sean
 * 2017/11/2.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private ResourceService resourceService;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private RoleService roleService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private OrgService orgService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public BaseRepository<User, Long> getBaseDao() {
        return this.userRepository;
    }

    /**
     * 获取当前用户信息,查询该用户所有菜单按钮
     *
     * @return AccountOut
     */

    @Override
    public AccountOut getMeInfo() {
        String username = jwtTokenUtil.getUserName();
        Set<String> roleCodes = jwtTokenUtil.getRoleCodes();
        AccountOut accountOut = new AccountOut();
        accountOut.setUserName(username);
        User user = userRepository.findByAccount_Username(username);
        BeanUtils.copyProperties(user, accountOut);
        //查询所有数据字典
//        List<DataDictionary> dicList = dataDictionaryRepository.findAll();
//        // 根据code将数据字典分类
//        List<Map<String, String>> collect = dicList.stream()
//                .map(DataDictionary -> {
//                    Map<String, String> dicData = new HashMap<>();
//                    dicData.put("code", DataDictionary.getCode());
//                    dicData.put("key", DataDictionary.getDataKey());
//                    dicData.put("value", DataDictionary.getDataValue());
//                    return dicData;
//                }).collect(Collectors.toList());// todo 优化
//        Map<String, List<Map<String, String>>> code = collect.stream().collect(groupingBy(item -> item.get("code")));
//        accountOut.setDic(code);
        return resourceService.getMenu(accountOut, roleCodes);
    }

    /**
     * 账户名是否可用
     *
     * @param userName 帐户名
     * @return 是否可用
     */
    @Override
    public boolean isUser(String userName) {
        return findUserByLoginName(userName) == null;
    }

    /**
     * 添加用户
     *
     * @param userSaveVo 前端数据
     */
    @Override
    public void createUser(UserSaveVo userSaveVo, String orgCode){
        Org org=orgService.find(userSaveVo.getOrgId());
        if (!org.getLevelCode().startsWith(orgCode)) {
            throw new CheckedException(ResultEnum.ORG_NOT_EXIST);
        }
        User user = new User();
        //账户
        user.getAccount().setUsername(userSaveVo.getUsername());
        BeanUtils.copyProperties(userSaveVo, user);
        user.setOrg(org);
        userRepository.saveAndFlush(user);
        //角色
        String[] roleIds = userSaveVo.getRoleId().split(",");
        List<Role> roles = roleRepository.findByIdIn(roleIds);
        if (roles.size() == 0) {
            throw new CheckedException(ResultEnum.DATA_NOT_EXIST);
        }
        roles.forEach(role -> createRoleAdmin(user, role));
    }
    /**
     * 建立角色账户关联
     *
     * @param user 包含角色ID的试图
     * @param role 账户实体
     */
    @Override
    public void createRoleAdmin(User user, Role role) {
        // // todo 组织关联验证
//        RoleUser roleUser = new RoleUser();
//        roleUser.setRole(role);
//        roleUser.setUser(user);
//        roleUserRepository.save(roleUser);
    }

    /**
     * 修改用户
     *
     * @param userSaveVo 前端数据
     */
    @Override
    public void updateUser(Long id, UserSaveVo userSaveVo, String orgCode){
        Org org=orgService.find(userSaveVo.getOrgId());
        if (!org.getLevelCode().startsWith(orgCode)) {
            throw new CheckedException(ResultEnum.ORG_NOT_EXIST);
        }
        User user = find(id);
        if (!user.getOrg().getLevelCode().startsWith(orgCode)) {
            throw new CheckedException(ResultEnum.ORG_NOT_EXIST);
        }
        BeanUtils.copyProperties(userSaveVo, user);
        userRepository.saveAndFlush(user);
        //角色
        String[] resourceIdArray = userSaveVo.getRoleId().split(",");
        // todo 组织关联验证
//        Set<Long> ids = new HashSet<>(roleUserRepository.findRoleByUserId(user.getId()));
//        List<Set<Long>> retainList = BusinessUtils.getRetainList(ids, new HashSet<>(Arrays.stream(resourceIdArray).map(Long::valueOf).collect(Collectors.toList())));
//        //删除旧差集
//        if (retainList.get(0).size() > 0) {
//            roleUserRepository.deleteforRoleIdsAndUserId(retainList.get(0), user.getId());
//        }
//        //添加新差集
//        List<Role> roles = (List<Role>) roleRepository.findByIdIn(retainList.get(1));
//        List<RoleUser> roleUsers = new ArrayList<>();
//        for (Role role : roles) {
//            RoleUser roleUser = new RoleUser();
//            roleUser.setRole(role);
//            roleUser.setUser(user);
//            roleUsers.add(roleUser);
//        }
//        roleUserRepository.saveAll(roleUsers);
    }


    /**
     * 删除用户
     *
     * @param id      用户id
     * @param orgCode 组织编码
     */
    @Override
    public void deleteUser(Long id, String orgCode){
        User user = find(id);
        if (user == null) {
            throw new CheckedException(ResultEnum.USER_NOT_EXIST);
        }
        if (!user.getOrg().getLevelCode().startsWith(orgCode)) {
            throw new CheckedException(ResultEnum.ORG_NOT_EXIST);
        }
        delete(user.getId());
        // todo 组织关联验证
        // roleUserRepository.deleteByUser(user);
    }

    /**
     * 批量删除
     *
     * @param id      用户ids
     * @param orgCode 组织编码
     */
    @Override
    public void batchesDelete(String id, String orgCode) {
        List<String> ids = Arrays.asList(id.split(","));
        List<User> users = userRepository.findByIdIn(ids);
        userRepository.batchesDelete(ids, orgCode);
        // todo 组织关联验证
        // roleUserRepository.deleteByUserIn(users);
    }

    /**
     * 根据项目code查询所有用户
     *
     * @param code 项目code
     * @return 组织下所有的labels
     */
    @Override
    public List<Map<String, String>> findByOrgCode(String code) {
        return userRepository.findByOrgCodeforAccount(code);
    }

    /**
     * 用户详情
     *
     * @param id 用户主键
     * @return UserDetailsVo
     */
    @Override
    public UserDetailsVo findUserDetail(Long id) {
        UserDetailsVo userDetailsVo = new UserDetailsVo();
        User user = find(id);
        Org org = user.getOrg();
        BeanUtils.copyProperties(user, userDetailsVo);
        userDetailsVo.setCreatedBy(user.getCreatedBy());
        userDetailsVo.setUsername(user.getAccount().getUsername());
        userDetailsVo.setOrgName(org.getName());
        userDetailsVo.setOrgType(org.getOrgType());
        userDetailsVo.setRemark(org.getRemark());
        //角色
        List<Role> roles = new ArrayList<>(user.getRoles());
        List<UserDetailRoleVo> userDetailRoleVos = new ArrayList<>();
        for (Role role : roles) {
            UserDetailRoleVo userDetailRoleVo = new UserDetailRoleVo();
            userDetailRoleVo.setName(role.getName());
            // todo
         //   userDetailRoleVo.setResourceName(roleResourceRepository.findResourceNameByRole(role));
            userDetailRoleVos.add(userDetailRoleVo);
        }
        userDetailsVo.setRoles(userDetailRoleVos);
        return userDetailsVo;
    }

    /**
     *  根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User findUserByLoginName(String username) {
        return userRepository.findByAccount_Username(username);
    }

    @Override
    public UserEditVo findUserEdit(Long id, String username) {
        User user = find(id);
        Set<Long> roles=user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
        List<String> labels =roleService.findLabel(username).stream()
                .map(m->m.get("value")).collect(Collectors.toList());
        if (!labels.containsAll(roles)){
            throw new CheckedException(ResultEnum.USER_EDIT_DISABLED);
        }
        UserEditVo userEditVo = new UserEditVo();
        BeanUtils.copyProperties(user, userEditVo);
        userEditVo.setRoleId(roles);
        userEditVo.setOrgId(user.getOrg().getLevelCode());
        return userEditVo;
    }

    /**
     * 系统内激活
     *
     * @param id       用戶id
     * @param password 密码
     * @return
     */
    @Override
    public StateResult activeAccount(Long id, String password) {
        User user = find(id);
        if (!user.isAccountNonExpired()) {
            user.getAccount().setActive(true);
            user.getAccount().setExpireTime(new Date(System.currentTimeMillis() + 2592000000L));
            user.getAccount().setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return StateResult.success();
        }else{
            throw new CheckedException(ResultEnum.ACTIVE_FAILED);
        }
    }

    /**
     * 锁定/解锁
     *
     * @param id 用户id
     */
    @Override
    public void locked(Long id) {
        User user = find(id);
        user.getAccount().setLocked(!user.getAccount().isLocked());
        userRepository.save(user);
    }

    /**
     * 退出登陆
     *
     * @return success
     */
    @Override
    public StateResult logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return StateResult.success();
    }

    /**
     * 修改密码
     *
     * @param username    账户名
     * @param map  oldPassword 旧密码  password 新密码
     */
    @Override
    public void modifyPassword(String username, Map<String,String> map) {
        User user = findUserByLoginName(username);
        if (map.get("phone")!=null&&Objects.equals(user.getPhone(),"")){
             user.setPhone(map.get("phone"));
        }
        if (!passwordEncoder.matches(map.get("oldPassword"), user.getAccount().getPassword())) {
            throw new CheckedException(ResultEnum.PASSWORD_NOT_MATCHING);
        }
        long pwdExpDay = 1000 * 24 * 60 * 60 * 1000;
        Date date = new Date(System.currentTimeMillis() + pwdExpDay);
        user.getAccount().setExpireTime(date);
        user.getAccount().setPassword(passwordEncoder.encode(map.get("password")));
        save(user);
    }

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
    @Override
    public PageResult page(Pageable pageable, String org, String realName, String email, String phone, String username, String orgCode, String roleId) {
        Specification<User> querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.get("account").get("username").as(String.class), "Sysadmin"));
            Join<Org,User> orgJoin =root.join("org", JoinType.LEFT);
            if (null != realName) {
                predicates.add(criteriaBuilder.like(root.get("realName").as(String.class), "%" + realName + "%"));
            }
            if (null != email) {
                predicates.add(criteriaBuilder.like(root.get("email").as(String.class), "%" + email + "%"));
            }
            if (null != phone) {
                predicates.add(criteriaBuilder.like(root.get("phone").as(String.class), "%" + phone + "%"));
            }
            if (null != roleId) {
              //  Join<User, RoleUser> join = root.join("roles", JoinType.LEFT);
               // predicates.add(criteriaBuilder.equal(join.get("role").get("id").as(String.class), roleId));
            }
            if (null != username) {
                predicates.add(criteriaBuilder.like(root.get("account").get("username").as(String.class), "%" + username + "%"));
            }
            if (null != orgCode && orgCode.startsWith(org)) {
                predicates.add(criteriaBuilder.like(orgJoin.get("levelCode").as(String.class), orgCode + "%"));
            } else {
                predicates.add(criteriaBuilder.like(orgJoin.get("levelCode").as(String.class), org + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<User> page = findAll(querySpecifi, pageable);
        return PageResult.getPageVo(page);
    }


    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param userName 账户名
     */
    @Override
    public StateResult sendCode(String phone, String userName) {
        User user = userRepository.findByAccount_Username(userName);
        if (user == null || (!user.getPhone().equals(phone))) {
            throw new CheckedException(ResultEnum.ACCOUNT_OR_PHONE_ERROR);
        }
//        //获取短信验证码
//        VerificationResponseVo verificationResponse = msVcmService.getIdentifyCodeV2(phone, "pateo");
//        if (("0").equals(verificationResponse.getStatus().getCode())) {
//            return StateResult.success();
//        } else {
//            // 获取验证码失败
//            throw new CheckedException(verificationResponse.getStatus().getCode(),
//                    verificationResponse.getStatus().getDescription());
//        }
        return StateResult.success();
    }

    /**
     * 重置密码
     *
     * @param userName 用户名
     * @param password 密码
     * @param phone    手机号
     * @param code     验证码
     */
    @Override
    public DataResult resetPassword(String userName, String password, String phone, String code) {
//        if (!"0".equals(msVcmService.queryIdentifyCode(phone, code).getCode())) {
//            throw new CheckedException(ResultEnum.CODE_ERROR);
//        }
        User user = userRepository.findByAccount_Username(userName);

        long pwdExpDay = 1000 * 24 * 60 * 60 * 1000;
        Date date = new Date(System.currentTimeMillis() + pwdExpDay);
        int result = userRepository.updatePassword(user.getId(), passwordEncoder.encode(password), date);
        if (result > 0) {
            Map<String, Object> map = new HashMap(2);
            map.put("userName", user.getUsername());
            map.put("expireTime", date);
            return DataResult.success(map);
        }
        throw new CheckedException(ResultEnum.ACTIVE_FAILED);

    }

    @Override
    public void updateExpireTime(User user) {
        Date date = new Date(System.currentTimeMillis() + 1000 * 24 * 60 * 60 * 1000);
        user.getAccount().setExpireTime(date);
        userRepository.save(user);
    }
}
