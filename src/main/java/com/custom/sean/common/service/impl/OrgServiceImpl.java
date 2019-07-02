package com.custom.sean.common.service.impl;

import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.domain.Org;
import com.custom.sean.common.domain.OrgBrand;
import com.custom.sean.common.domain.User;
import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.repository.OrgBrandRepository;
import com.custom.sean.common.repository.OrgRepository;
import com.custom.sean.common.repository.UserRepository;
import com.custom.sean.common.service.BrandService;
import com.custom.sean.common.service.OrgService;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseServiceImpl;
import com.custom.sean.common.utils.menu.OrgType;
import com.custom.sean.common.utils.vo.ResultEnum;
import com.custom.sean.common.utils.vo.rbac.OrgInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author sean
 * @date 2018/1/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgServiceImpl extends BaseServiceImpl<Org, Long> implements OrgService {
    @Resource
    private OrgRepository orgRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BrandService brandService;

    @Resource
    private OrgBrandRepository orgBrandRepository;

    @Override
    public BaseRepository<Org, Long> getBaseDao() {
        return this.orgRepository;
    }

    @Override
    public OrgInfo getTree(String orgCode) {
        List<Org> orgs = orgRepository.findByLevelCodeLike(orgCode+"%");
        Org org = orgs.stream().filter(o -> o.getLevelCode().equals(orgCode)).findAny().get();
        return toTree(orgs, org);
    }

    /**
     * 树形资源递归
     *
     * @param orgList
     * @param parent
     */
    @Override
    public OrgInfo toTree(List<Org> orgList, Org parent) {
        OrgInfo orgInfo = new OrgInfo();
        BeanUtils.copyProperties(parent, orgInfo);
        orgInfo.setId(parent.getId());
        orgInfo.setValue(parent.getId());
        orgInfo.setLabel(parent.getName());
        orgInfo.setName(parent.getName());
        orgInfo.setBrandIds(parent.getBrands().stream().map(OrgBrand::getBrand).map(Brand::getId).collect(Collectors.toList()));
        orgInfo.setOrgType(parent.getOrgType().ordinal());
        List<OrgInfo> children = new ArrayList();
        for (Org org : orgList) {
            if (org.getParentId().equals(parent.getId())) {
                children.add(toTree(orgList, org));
            }
        }
        orgInfo.setChildren(children);
        return orgInfo;
    }

    /**
     * 编码是否可用
     *
     * @param code
     * @return
     */
    @Override
    public boolean isUser(String code) {
        Org org = new Org();
        org.setCode(code);
        return !orgRepository.exists(Example.of(org));
    }

    /**
     * 新增组织
     *
     * @param org
     */
    @Override
    public void add(Org org) {
        String[] bIds=StringUtils.split(org.getBrandIds(),",");
        Collection<Brand> brands=brandService.findList(Arrays.stream(bIds).map(Long::valueOf).collect(Collectors.toList()));
        Org parent = find(org.getParentId());
        org.setLevelCode(parent.getLevelCode()+"-"+org.getCode());
        org.setOrgType(OrgType.values()[parent.getOrgType().ordinal() + 1]);
        Set<OrgBrand> orgBrands= new HashSet<>();
        brands.forEach(brand -> orgBrands.add(new OrgBrand(org,brand)));
        org.setBrands(orgBrands);
        save(org);
    }

    /**
     * 删除组织
     * @param id 主键
     * @param currentOrgCode 当前登陆用户组织编码
     */
    @Override
    public void deleteOne(Long id, String currentOrgCode) {
        Org org=find(id);
        String orgCode=org.getLevelCode();
        if (!orgCode.startsWith(currentOrgCode)) {
            throw new CheckedException(ResultEnum.ORG_NOT_EXIST);
        }
        if (userRepository.findByOrgCode(orgCode) > 0) {
            throw new CheckedException(ResultEnum.USER_BIND_EXIST);
        }
        Org children = new Org();
        children.setParentId(id);
        if (orgRepository.exists(Example.of(children))) {
            throw new CheckedException(ResultEnum.ORG_HAS_CHILDREN);
        }
        orgRepository.delete(org);
    }


    /**
     * 初始化账号-组织-品牌-项目
     *
     * @param user
     */
    @Override
    public void initByUser(User user) {
        user.getAuthorities();
        Org org = user.getOrg();
        List<String> brands = org.getBrands().stream().map(OrgBrand::getBrand).map(Brand::getCode).collect(Collectors.toList());
        user.setBrandCodes(new HashSet<>(brands));
    }

    /**
     * 查询根据关键子查询所有符合条件的组织
     * @param orgCode 账户所有组织
     * @param name input参数
     * @return vos
     */
    @Override
    public List<Map<String,String>> findLikeName(String orgCode, String name) {
        return orgRepository.findByNameLike(orgCode,name);
    }

    /**
     * 修改组织
     * @param orgVo Vo
     */
    @Override
    public void update(Long id, Org orgVo) {
        Org org=find(id);
        if (id.equals(orgVo.getParentId())){
            throw new CheckedException(ResultEnum.ORG_ONESELF_PARENT);
        }
        List<Long> ids=org.getBrands().stream().map(OrgBrand::getBrand).map(Brand::getId).collect(Collectors.toList());
        if (!orgVo.getBrandIds().equals(StringUtils.join(ids,","))){
            String[] bIds=StringUtils.split(orgVo
                    .getBrandIds(),",");
            Collection<Brand> brands=brandService.findList(Arrays.stream(bIds).map(Long::valueOf).collect(Collectors.toList()));
            orgBrandRepository.deleteByOrg(org);
            Set orgBrands= new HashSet();
            brands.forEach(brand -> orgBrands.add(new OrgBrand(org,brand)));
            org.setBrands(orgBrands);
        }
        if (!org.getParentId().equals(orgVo.getParentId())){
            Org parent = find(orgVo.getParentId());
            org.setLevelCode(parent.getLevelCode()+"-"+org.getCode());
            org.setParentId(orgVo.getParentId());
            org.setOrgType(OrgType.values()[parent.getOrgType().ordinal() + 1]);
        }
        org.setName(orgVo.getName());
        org.setRemark(orgVo.getRemark());
        save(org);
    }
}
