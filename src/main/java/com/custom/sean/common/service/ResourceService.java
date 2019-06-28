package com.custom.sean.common.service;

import com.custom.sean.common.domain.Resource;
import com.custom.sean.common.utils.jpa.BaseService;
import com.custom.sean.common.utils.vo.rbac.AccountOut;
import com.custom.sean.common.utils.vo.rbac.AuthInfo;
import com.custom.sean.common.utils.vo.rbac.ResourceInfo;
import com.custom.sean.common.utils.vo.rbac.ResourceVo;

import java.util.List;
import java.util.Set;

/**
 * @author sean
 * @date 2017/11/6
 */
public interface ResourceService extends BaseService<Resource, String> {

    /**
     * 查询该用户所有菜单按钮
     *
     * @param accountOut
     * @return
     */

    AccountOut getMenu(AccountOut accountOut, Set<String> codes);

    /**
     * 菜单递归
     *
     * @param resources
     * @param parent
     * @return
     */
    AuthInfo getAuthInfo(List<Resource> resources, Resource parent);

    /**
     * 获取所有资源树
     *
     * @return
     */
    ResourceInfo getAllTree(String name);

    /**
     * 树形资源递归
     *
     * @param resourceSet
     * @param parent
     * @return
     */
    ResourceInfo toTree(List<Resource> resourceSet, Resource parent);

    /**
     * 根据资源ID获取资源信息
     *
     * @param id 资源ID
     * @return ResourceInfo 资源信息
     * @date 2015年7月10日下午7:01:48
     * @since 1.0.0
     */

    ResourceVo getInfo(String id);
    /**
     * 新增资源
     *
     * @param resourceVo 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date 2017年11月07日下午7:01:51
     * @since 1.0.0
     */
    void create(ResourceVo resourceVo,String username);


    /**
     * 更新资源
     *
     * @param resourceInfo 页面传入的资源信息
     * @return ResourceInfo 资源信息
     * @date 2015年7月10日下午7:01:54
     * @since 1.0.0
     */
    void update(String id, ResourceInfo resourceInfo);


    /**
     * 根据指定ID删除资源信息
     *
     * @param id 资源ID
     * @date 2015年7月10日下午7:01:57
     * @since 1.0.0
     */
    void deleteResource(String id);

    /**
     * 获取角色资树
     *
     * @param id
     * @return
     */
    ResourceInfo getRoleTree(String id);

    /**
     * 获取根节点
     * @return
     */
    Resource getRootResource();
}
