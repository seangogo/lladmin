package com.custom.sean.common.service.impl;

import com.custom.sean.common.domain.Resource;
import com.custom.sean.common.domain.Role;
import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.repository.ResourceRepository;
import com.custom.sean.common.repository.RoleRepository;
import com.custom.sean.common.service.RoleService;
import com.custom.sean.common.utils.basics.BusinessUtils;
import com.custom.sean.common.utils.basics.JwtTokenUtil;
import com.custom.sean.common.utils.basics.StringUtils;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseServiceImpl;
import com.custom.sean.common.utils.vo.ResultEnum;
import com.custom.sean.common.utils.vo.properties.SecurityProperties;
import com.custom.sean.common.utils.vo.rbac.RoleTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SecurityProperties securityProperties;

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
    public RoleTree getTree() {
        Set<String> codes=jwtTokenUtil.getRoleCodes();
        if (codes.size() == 1){
            List<Role> roles = roleRepository.findByLevelCodeLikeOrderByCreatedDate(codes.toArray()[0]+"%");
            return toTree(roles,roles.get(0));
        } else {
            RoleTree roleTree =new RoleTree();
            for (String code : codes) {
                List<Role> roles = roleRepository.findByLevelCodeLikeOrderByCreatedDate(code+"%");
                roleTree.getChildren().add(toTree(roles,roles.get(0)));
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
    @Override
    public RoleTree toTree(List<Role> roles, Role parent) {
        RoleTree roleTree = new RoleTree();
        BeanUtils.copyProperties(parent, roleTree);
        List<RoleTree> children = new ArrayList();
        for (Role role : roles) {
            if (parent.getId().equals(role.getParentId())) {
                children.add(toTree(roles, role));
            }
        }
        roleTree.setChildren(children);
        return roleTree;
    }

    /**
     * 新增角色
     * @param role
     */
    @Override
    public void create(Role role){
        Role parent = find(role.getParentId());
        Role ex = new Role();
        ex.setName(role.getName());
        String code=BusinessUtils.getCode(role.getName(),this.getBaseDao(),Role.class);
        role.setCode(code);
        role.setLevelCode(parent.getLevelCode()+"-"+role.getCode());
        save(role);
        // todo 继承资源
    }
    
    /**
     * 通过roleId 查询资源id
     * @param roleId
     * @return
     */
    @Override
    public Map<String,Object> getRoleResources(Long roleId){
        Role role=find(roleId);
        Role parent=find(role.getParentId());
        List<Long> allIds=role.getResources().stream().map(Resource::getId).collect(Collectors.toList());
        List<Long> parentIds=new ArrayList<>();
        // roleResourceRepository.findIdsByRid(parent.getId());
        Set<String> pid=allIds.size()>0?resourceRepository.findPidByIdIn(allIds.toArray()):new HashSet<>();
        allIds.removeAll(pid);
        Map<String,Object> map=new HashMap<>(2);
        map.put("checked",allIds);
        map.put("halfChecked",pid);
        map.put("activeKeys",parentIds);
        return map;
    }

    /**
     * 角色绑定资源
     * @param roleId 角色id
     * @param resourceIds 绑定的资源ids
     */
    @Override
    public void setRoleResources(Long roleId, String resourceIds){
        Role role = find(roleId);
        if (role.getCode().equals(securityProperties.getInit().getSysadmin())){
            throw new CheckedException(ResultEnum.SUPER_CANNOT_UPDATE);
        }
        resourceIds = StringUtils.removeEnd(resourceIds, ",");
        String[] resourceIdArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(resourceIds, ",");
        Set<Long> ids= new HashSet<>(role.getResources().stream().map(Resource::getId).collect(Collectors.toList()));
        List<Set<Long>> retainList=BusinessUtils.getRetainList(ids,new HashSet<>(Arrays.stream(resourceIdArray).map(Long::valueOf).collect(Collectors.toList())));
        //删除旧差集
        if (retainList.get(0).size()>0){
            //todo  roleResourceRepository
           // roleResourceRepository.deleteforResourceIdsAndRoleId(retainList.get(0),roleId);
        }
        //添加新差集
        List<Resource> resources= (List<Resource>) resourceRepository.findByIdIn(retainList.get(1));
//        List<RoleResource> roleResources=new ArrayList();
//        for (Resource resource : resources) {
//            RoleResource roleResource = new RoleResource();
//            roleResource.setRole(role);
//            roleResource.setResource(resource);
//            roleResources.add(roleResource);
//        }
        // todo           roleResourceRepository
        // roleResourceRepository.saveAll(roleResources);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void deleteRole(Long id){
        Role example=new Role();
        example.setParentId(id);
        if (roleRepository.exists(Example.of(example))) {
            throw new CheckedException(ResultEnum.RESOURCE_HAS_CHILDREN);
        }
        // todo 组织关联验证
        Role role=find(id);
        if (role.getUsers().size()>0){
            throw new CheckedException(ResultEnum.DATA_BIND_NOT_DELETE);
        }
        // todo 组织关联验证
        // roleResourceRepository.deleteByRole_Id(id);
        delete(role);
    }

    /**
     * 根据项目code查询所有角色
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String,String>> findLabel(String username) {
        if (Objects.equals(username,securityProperties.getInit().getSysadmin())){
            return roleRepository.findSuperLabel(username);
        }
        return roleRepository.findLabel(username);
    }

    /**
     * 修改角色
     * @param id 角色id
     * @param roleVo vo
     */
    @Override
    public void update(Long id, Role roleVo) {
        Role role = find(id);
        if (role.getCode().equals(securityProperties.getInit().getSysadmin())){
            throw new  CheckedException(ResultEnum.SUPER_CANNOT_UPDATE);
        }
        roleVo.setVersion(role.getVersion());
        save(roleVo);
    }


    /**
     * 批量删除
     * @param id 角色id
     */
    @Override
    public void batchesDelete(String id) {
        List<String> ids=Arrays.asList(id.split(","));
        if (roleRepository.findUserCountByRoleIds(ids).size()>0){
            throw new CheckedException(ResultEnum.ROLES_BIND_NOT_DELETE);
        }
        for (String s : ids) {
            // // todo 组织关联验证
           //  roleResourceRepository.deleteByRole_Id(Long.valueOf(s));
        }
        roleRepository.deleteByIdIn(ids);
    }
}
