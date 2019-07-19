package com.seangogo.modules.system.service.impl;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.repository.RoleRepository;
import com.seangogo.modules.system.service.RoleService;
import com.seangogo.modules.system.service.dto.RoleTreeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 角色业务层实现类
 *
 * @author seang
 * @date 2019/7/19 16:00
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public BaseRepository<Role, Long> getBaseDao() {
        return this.roleRepository;
    }

    /**
     * 获取当前账户角色tree
     *
     * @return
     */
    @Override
    public RoleTreeDTO getTree() {
        Set<String> codes = jwtTokenUtil.getRoleCodes();
        if (codes.size() == 1) {
            List<Role> roles = roleRepository.findByLevelCodeLikeOrderByCreatedTime(codes.toArray()[0] + "%");
            return toTree(roles, roles.get(0));
        } else {
            RoleTreeDTO roleTree = new RoleTreeDTO();
            for (String code : codes) {
                List<Role> roles = roleRepository.findByLevelCodeLikeOrderByCreatedTime(code + "%");
                roleTree.getChildren().add(toTree(roles, roles.get(0)));
            }
            return roleTree;
        }
    }

    /**
     * 树形资源递归
     *
     * @param roles
     * @param parent
     */
    public RoleTreeDTO toTree(List<Role> roles, Role parent) {
        RoleTreeDTO roleTree = new RoleTreeDTO();
        BeanUtils.copyProperties(parent, roleTree);
        List<RoleTreeDTO> children = new ArrayList();
        for (Role role : roles) {
            if (parent.getId().equals(role.getParentId())) {
                children.add(toTree(roles, role));
            }
        }
        roleTree.setChildren(children);
        return roleTree;
    }
}
