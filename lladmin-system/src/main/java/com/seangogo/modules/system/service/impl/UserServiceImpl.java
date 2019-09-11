package com.seangogo.modules.system.service.impl;

import com.seangogo.base.exception.CheckedException;
import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.common.enums.ResultEnum;
import com.seangogo.common.utils.PageResult;
import com.seangogo.common.utils.ValidationUtil;
import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.domain.enums.ResourceType;
import com.seangogo.modules.system.repository.RoleRepository;
import com.seangogo.modules.system.repository.UserRepository;
import com.seangogo.modules.system.service.UserService;
import com.seangogo.modules.system.service.dto.PageQueryDTO;
import com.seangogo.modules.system.service.vo.AuthInfo;
import com.seangogo.modules.system.service.vo.MenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 用户业务层实现类
 *
 * @author seang
 * @date 2019/7/3 11:34
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseRepository<User, Long> getBaseDao() {
        return this.userRepository;
    }

    /**
     * 通过用户名称查找用户
     *
     * @param userName 登陆名称
     * @return 用户dto
     */
    @Override
    public User findByName(String userName) {
        User user;
        if (ValidationUtil.isEmail(userName)) {
            user = userRepository.findByEmail(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }
        if (user == null) {
            throw new CheckedException(ResultEnum.ACCOUNT_NOT_EXIST);
        } else {
            return user;
        }
    }

    /**
     * findUserInfo
     *
     * @param userName
     * @return
     */
    @Override
    public AuthInfo findUserInfo(String userName) {
        AuthInfo authInfo = new AuthInfo();
        User user = userRepository.findByUsername(userName);
        authInfo.setUserName(userName);
        authInfo.setAvatar(user.getAvatar());
        authInfo.setPhone(user.getPhone());
        authInfo.setEmail(user.getEmail());
        Set<Role> roles = roleRepository.findByRoles(user.getRoles());
        Set<Resource> resources = roles.stream().flatMap(role -> role.getResources().stream()).collect(Collectors.toSet());
        Optional<Resource> root = resources.stream().min(Comparator.comparing(Resource::getSort));
        if (!root.isPresent()) {
            throw new CheckedException(ResultEnum.MENU_ROOT_NODE_NOT_EXIST);
        }
        resources.remove(root.get());
        Map<ResourceType, List<Resource>> groupBy = resources.stream().collect(Collectors.groupingBy(Resource::getType));
        List<Resource> menus = groupBy.get(ResourceType.MENU);
        List<Resource> routes = groupBy.get(ResourceType.ROUTE);
        List<Resource> buttons = groupBy.get(ResourceType.BUTTON);
        if (routes != null) {
            menus.addAll(groupBy.get(ResourceType.ROUTE));
        }
        if (buttons != null) {
            authInfo.setButtons(buttons.stream().map(Resource::getCode).collect(Collectors.toSet()));
        }
        authInfo.setMenuInfo(getAuthInfo(menus, root.get()));
        return authInfo;
    }

    /**
     * 菜单递归
     *
     * @param resources
     * @param parent
     * @return
     */
    @Override
    public MenuInfo getAuthInfo(List<Resource> resources, Resource parent) {
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setName(parent.getName());
        menuInfo.setPath(parent.getCode());
        menuInfo.setIcon(parent.getIcon());
        menuInfo.setMenu(parent.getType().ordinal() == 0);
        List<MenuInfo> children = new ArrayList();
        for (Resource resource : resources) {
            if (resource.getParent().getId().equals(parent.getId())) {
                children.add(getAuthInfo(resources, resource));
            }
        }
        menuInfo.setChildren(children);
        return menuInfo;
    }

    @Override
    public PageResult<User> page(Pageable pageable, PageQueryDTO.UserQueryDto dto) {
        String levelCode = jwtTokenUtil.getDeptLevelCode();
        Specification<User> querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //  predicates.add(criteriaBuilder.notEqual(root.get("account").get("username").as(String.class), "Sysadmin"));
            if (null != dto.getRealName()) {
                predicates.add(criteriaBuilder.like(root.get("realName").as(String.class), "%" + dto.getRealName() + "%"));
            }
            if (null != dto.getEmail()) {
                predicates.add(criteriaBuilder.like(root.get("email").as(String.class), "%" + dto.getEmail() + "%"));
            }
            if (null != dto.getPhone()) {
                predicates.add(criteriaBuilder.like(root.get("phone").as(String.class), "%" + dto.getPhone() + "%"));
            }
            if (null != dto.getUsername()) {
                predicates.add(criteriaBuilder.like(root.get("account").get("username").as(String.class), "%" + dto.getUsername() + "%"));
            }
//            if (null != dto.getOrgCode() && dto.getOrgCode().startsWith(orgCode)) {
//                predicates.add(criteriaBuilder.like(orgJoin.get("levelCode").as(String.class), orgCode + "%"));
//            } else {
//                predicates.add(criteriaBuilder.like(orgJoin.get("levelCode").as(String.class), org + "%"));
//            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<User> page = userRepository.findAll(querySpecifi, pageable);
        return PageResult.getPageVo(page);
    }
}
