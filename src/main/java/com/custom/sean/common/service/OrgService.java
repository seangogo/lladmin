package com.custom.sean.common.service;

import com.custom.sean.common.domain.Org;
import com.custom.sean.common.domain.User;
import com.custom.sean.common.utils.jpa.BaseService;
import com.custom.sean.common.utils.vo.rbac.OrgInfo;


import java.util.List;
import java.util.Map;

/**
 *
 * @author sean
 * @date 2018/1/8
 */
public interface OrgService extends BaseService<Org,Long> {

    OrgInfo getTree(String orgCode);

    /**
     * 树形资源递归
     *
     * @param orgList
     * @param parent
     */
    OrgInfo toTree(List<Org> orgList, Org parent);

    /**
     * 编码是否可用
     *
     * @param code
     * @return
     */
    boolean isUser(String code);

    /**
     * 新增组织
     *
     * @param org
     */
    void add(Org org);

    /**
     * 删除组织
     * @param id 主键
     * @param currentOrgCode 当前登陆用户组织编码
     */
    void deleteOne(Long id, String currentOrgCode);

    /**
     * 初始化账号-组织-品牌-项目
     *
     * @param user
     */
    void initByUser(User user);

    /**
     * 查询根据关键子查询所有符合条件的组织
     * @param orgCode 账户所有组织
     * @param name input参数
     * @return vos
     */
    List<Map<String,String>> findLikeName(String orgCode, String name);

    /**
     * 修改组织
     * @param orgVo Vo
     */
    void update(Long id, Org orgVo);
}
