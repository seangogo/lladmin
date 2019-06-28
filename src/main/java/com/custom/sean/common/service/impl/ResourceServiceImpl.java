package com.custom.sean.common.service.impl;

import com.custom.sean.common.domain.*;
import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.repository.ResourceRepository;
import com.custom.sean.common.repository.RoleRepository;
import com.custom.sean.common.repository.RoleResourceRepository;
import com.custom.sean.common.repository.UserRepository;
import com.custom.sean.common.service.ResourceService;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseServiceImpl;
import com.custom.sean.common.utils.vo.ResultEnum;
import com.custom.sean.common.utils.vo.properties.SecurityProperties;
import com.custom.sean.common.utils.vo.rbac.AccountOut;
import com.custom.sean.common.utils.vo.rbac.AuthInfo;
import com.custom.sean.common.utils.vo.rbac.ResourceInfo;
import com.custom.sean.common.utils.vo.rbac.ResourceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sean
 * @date 2017/11/6
 */
@Service
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String> implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public BaseRepository<Resource, String> getBaseDao() {
        return this.resourceRepository;
    }

    /**
     * 查询该用户所有菜单按钮
     *
     * @param accountOut
     * @return
     */

    @Override
    public AccountOut getMenu(AccountOut accountOut, Set<String> codes) {
        List<Resource> menus = roleResourceRepository.findMenuByRoles(codes);
        AuthInfo authInfo = getAuthInfo(menus, resourceRepository.findByName("根节点"));
        accountOut.setButtons(roleResourceRepository.findButtonByRoles(codes));
        accountOut.setAuthInfo(authInfo);
        return accountOut;
    }

    /**
     * 菜单递归
     *
     * @param resources
     * @param parent
     * @return
     */
    @Override
    public AuthInfo getAuthInfo(List<Resource> resources, Resource parent) {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setName(parent.getName());
        authInfo.setPath(parent.getCode());
        authInfo.setIcon(parent.getIcon());
        authInfo.setMenu(parent.getType().ordinal() == 0);
        List<AuthInfo> children = new ArrayList();
        for (Resource resource : resources) {
            if (resource.getParent().getId().equals(parent.getId())) {
                children.add(getAuthInfo(resources, resource));
            }
        }
        authInfo.setChildren(children);
        return authInfo;
    }

    /**
     * 获取所有资源树
     *
     * @return
     */
    @Override
    public ResourceInfo getAllTree(String name) {
        Resource rootResource = resourceRepository.findByName("根节点");
        Specification querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.as(Resource.class), rootResource));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        List<Resource> resources = resourceRepository.findAll(querySpecifi, new Sort(Direction.ASC, "sort"));
        if (name != null) {
            List<Resource> resources2= resourceRepository.findByNameLikeAndParentOrderBySort("%"+name+"%",rootResource);
            ResourceInfo result = new ResourceInfo();
            BeanUtils.copyProperties(rootResource, result);
            result.setParentId(rootResource.getId());
            List<ResourceInfo> resourceInfos=new ArrayList<>();
            for (Resource resource : resources2) {
                resourceInfos.add(toTree(resources, resource));
            }
            result.setChildren(resourceInfos);
            return result;
        }
        return toTree(resources, rootResource);
    }

    /**
     * 树形资源递归
     *
     * @param resourceSet
     * @param parent
     * @return
     */
    @Override
    public ResourceInfo toTree(List<Resource> resourceSet, Resource parent) {
        ResourceInfo result = new ResourceInfo();
        BeanUtils.copyProperties(parent, result);
        result.setCreatedBy(parent.getCreatedBy());
        if (parent.getParent() != null) {
            result.setParentId(parent.getParent().getId());
        }
        List<ResourceInfo> children = new ArrayList();
        for (Resource resource : resourceSet) {
            if (parent.getId().equals(resource.getParent().getId())) {
                children.add(toTree(resourceSet, resource));
            }
        }
        result.setChildren(children);
        return result;
    }

    /**
     * 根据资源ID获取资源信息
     *
     * @param id 资源ID
     * @return ResourceInfo 资源信息
     * @date 2015年7月10日下午7:01:48
     * @since 1.0.0
     */
    @Override
    public ResourceVo getInfo(String id) {
        Resource exist=new Resource();
        exist.setId(id);
        if (!resourceRepository.exists(Example.of(exist))) {
            throw new CheckedException(ResultEnum.OBJ_NOT_EXIST);
        }
        Resource resource = find(id);
        ResourceVo resourceVo = new ResourceVo();
        BeanUtils.copyProperties(resource, resourceVo);
        return resourceVo;
    }

    /**
     * 新增资源
     *
     * @param resourceVo 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date 2017年11月07日下午7:01:51
     * @since 1.0.0
     */
    @Override
    public void create(ResourceVo resourceVo,String username){
        Resource parent = find(resourceVo.getParentId());
        Resource examine = resourceRepository.findByCode(resourceVo.getCode());
        if (examine != null) {
            throw new CheckedException(ResultEnum.DATA_EXIST);
        }
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceVo, resource);
        parent.addChild(resource);
        resource.setParent(parent);
        resource.setLevelCode(parent.getLevelCode()+"-"+resourceVo.getCode());
        resource.setSort(resourceRepository.findMaxSort() + 1);
        resourceRepository.save(resource);
        User user=userRepository.findByAccount_Username(username);
        RoleResource roleResource = new RoleResource();
        roleResource.setResource(resource);
        for (RoleUser roleUser : user.getRoles()) {
            roleResource.setRole(roleUser.getRole());
            roleResourceRepository.save(roleResource);
        }
        // todo 优化魔法值
        Role role = roleRepository.findByCode("cjgly");
        roleResource.setRole(role);
        roleResourceRepository.save(roleResource);
    }


    /**
     * 更新资源
     *
     * @param resourceInfo 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date 2015年7月10日下午7:01:54
     * @since 1.0.0
     */
    @Override
    public void update(String id, ResourceInfo resourceInfo) {
        Resource resource = find(id);
        BeanUtils.copyProperties(resourceInfo, resource);
        if (!resourceInfo.getParentId().equals(resource.getParent())){
            Resource newParent=find(resourceInfo.getParentId());
            String newLeaveCode = newParent.getLevelCode() + "-" + resource.getCode();
            resource.setLevelCode(newLeaveCode);
            resource.setParent(newParent);
        }
        resourceRepository.save(resource);
    }


    /**
     * 根据指定ID删除资源信息
     *
     * @param id 资源ID
     * @date 2015年7月10日下午7:01:57
     * @since 1.0.0
     */
    @Override
    public void deleteResource(String id) {
        List<Resource> resources = resourceRepository.findByParent_Id(id);
        if (resources.size() > 0) {
            throw new CheckedException(ResultEnum.RESOURCE_HAS_CHILDREN);
        }

        String dSql = "delete from auth_role_resource where resource_sid = '" + id + "'";
        Query query = em.createNativeQuery(dSql);
        String dSql1 = "delete from auth_resource where sid = '" + id + "'";
        Query query1 = em.createNativeQuery(dSql1);
        query.executeUpdate();
        query1.executeUpdate();
    }

    /**
     * 获取角色资树
     *
     * @param id
     * @return
     */
    @Override
    public ResourceInfo getRoleTree(String id) {
        Resource resource = resourceRepository.findByName("根节点");
        ResourceInfo resourceInfo = resource.roleToTree(roleResourceRepository.findIdsByRid(id));
        return resourceInfo;
    }

    @Override
    public Resource getRootResource() {
        return resourceRepository.findByName("根节点");
    }

//    @Override
//    public void move(String id, boolean up) {
//        Resource resource =find(id)
//        int index = resource.getSort();
//        List<Resource> children = resource.getParent().getChildren();
//        for (int i = 0; i < children.size(); i++) {
//            Resource current = children.get(i);
//            if (current.getId().equals(id)) {
//                if (up) {
//                    if (i != 0) {
//                        Resource pre = children.get(i - 1);
//                        resource.setSort(pre.getSort());
//                        pre.setSort(index);
//                        resourceRepository.save(pre);
//                    }
//                } else {
//                    if (i != children.size() - 1) {
//                        Resource next = children.get(i + 1);
//                        resource.setSort(next.getSort());
//                        next.setSort(index);
//                        resourceRepository.save(next);
//                    }
//                }
//            }
//        }
//        resourceRepository.save(resource);
//    }
}
