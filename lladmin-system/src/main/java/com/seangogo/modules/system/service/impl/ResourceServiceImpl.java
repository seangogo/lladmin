package com.seangogo.modules.system.service.impl;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.modules.system.service.vo.ResourceDto;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.repository.ResourceRepository;
import com.seangogo.modules.system.repository.RoleRepository;
import com.seangogo.modules.system.service.ResourceService;
import com.seangogo.modules.system.service.dto.ResourceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author seang
 * @date 2019/7/5 14:38
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseRepository<Resource, Long> getBaseDao() {
        return this.resourceRepository;
    }


    @Override
    public Collection<GrantedAuthority> userToGrantedAuthorities(User user) {
        Set<Role> roles = roleRepository.findByUsers_Id(user.getId());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getLevelCode()));
        });
        List<SimpleGrantedAuthority> resources = roles.stream().flatMap(role -> role.getResources().stream())
                .map(resource -> new SimpleGrantedAuthority(resource.getCode()))
                .collect(Collectors.toList());
        authorities.addAll(resources);
        return authorities;
    }

    /**
     * 获取所有资源树
     *
     * @param name
     * @return 结果集
     */
    @Override
    public ResourceDTO getAllTree(String name) {
        List<Resource> resources = resourceRepository.findAll(new Sort(Sort.Direction.ASC, "sort"));
        Resource root = resources.stream().min(Comparator.comparing(Resource::getSort)).orElse(null);
        resources.remove(root);
        if (name != null) {
            List<Resource> resourcesForName = resourceRepository.findByNameLikeAndParentOrderBySort("%" + name + "%", root);
            ResourceDTO dto = new ResourceDTO();
            BeanUtils.copyProperties(root, dto);
            dto.setParentId(root.getId());
            List<ResourceDTO> resourceDTOS = new ArrayList<>();
            for (Resource resource : resourcesForName) {
                resourceDTOS.add(toTree(resources, resource));
            }
            dto.setChildren(resourceDTOS);
            return dto;
        }
        return toTree(resources, root);
    }

    @Override
    public void create(ResourceDto dto) {
        Resource parent = find(dto.getParentId());
        //编码检查去除
//        Resource examine = resourceRepository.findByCode(resourceVo.getCode());
//        if (examine != null) {
//            throw new CheckedException(ResultEnum.DATA_EXIST);
//        }
        Resource resource = new Resource();
        resource.setParent(parent);
        resource.setLevelCode(parent.getLevelCode() + "-" + resource.getCode());
        resource.setSort(resourceRepository.findMaxSort() + 1);
        resource.setCode(dto.getCode());
        resource.setIcon(dto.getIcon());
        resource.setName(dto.getName());
        resource.setRemark(dto.getRemark());
        resource.setType(dto.getType());
        resourceRepository.save(resource);
        // 绑定超级管理员
        Role role = roleRepository.findByCode("cjgly");
        role.getResources().add(resource);
        roleRepository.save(role);
    }

    /**
     * 修改资源
     *
     * @param id  唯一标识
     * @param dto dto
     */
    @Override
    public void update(Long id, ResourceDto dto) {
        Resource resource = find(id);
        if (!dto.getParentId().equals(resource.getParent().getId())) {
            Resource newParent = find(dto.getParentId());
            String newLeaveCode = newParent.getLevelCode() + "-" + resource.getCode();
            resource.setLevelCode(newLeaveCode);
            resource.setParent(newParent);
        }
        BeanUtils.copyProperties(dto, resource);
        resourceRepository.save(resource);
    }

    /**
     * 树形资源递归
     *
     * @param resourceSet
     * @param parent
     * @return
     */
    public ResourceDTO toTree(List<Resource> resourceSet, Resource parent) {
        ResourceDTO result = new ResourceDTO();
        BeanUtils.copyProperties(parent, result);
        result.setCreatedBy(parent.getCreatedBy());
        if (parent.getParent() != null) {
            result.setParentId(parent.getParent().getId());
        }
        List<ResourceDTO> children = new ArrayList();
        for (Resource resource : resourceSet) {
            if (parent.getId().equals(resource.getParent().getId())) {
                children.add(toTree(resourceSet, resource));
            }
        }
        result.setChildren(children);
        return result;
    }
}
