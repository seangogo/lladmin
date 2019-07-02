package com.custom.sean.common.repository;

import com.custom.sean.common.domain.Org;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * 组织
 * @author sean
 * @date 2018/1/8
 */
public interface OrgRepository extends BaseRepository<Org, Long> {
    /**
     * 查找当前组织和子组织
     * @param levelCode 层级编码
     * @return 集合
     */
    List<Org> findByLevelCodeLike(String levelCode);

    /**
     * 通过 组织类型查询所有组织
     * @param orgType 组织类型
     * @return orgs
     */
   // List<Org> findByOrgType(OrgType orgType);

    /**
     * 通过组织编码查询组织
     * @param code 编码
     * @return org
     */
   // Org findByCode(String code);

    /**
     * 通过name查找对应的组织选项
     * @param orgCode 账户组织
     * @param name parameter
     * @return 集合
     */
    @Query("select new map(o.name as label, o.levelCode as value) from Org o where o.levelCode like ?1%  and o. name like %?2% ")
    List<Map<String,String>> findByNameLike(String orgCode, String name);

    /**
     * 根据层级编码查询下级经销商Code列表
     * @param levelCode 层级编码
     * @return 集合
     */
  //  @Query("select p.code from Org p where p.orgType = 3 and p.levelCode like CONCAT(?1,'%')")
  //  List<String> findByDealerListByLevelCode(String levelCode);
}
