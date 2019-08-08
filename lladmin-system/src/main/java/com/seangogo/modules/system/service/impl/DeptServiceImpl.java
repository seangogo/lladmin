package com.seangogo.modules.system.service.impl;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.domain.Dept;
import com.seangogo.modules.system.repository.DeptRepository;
import com.seangogo.modules.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author seang
 * @date 2019/8/6 15:13
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept, Long> implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public BaseRepository<Dept, Long> getBaseDao() {
        return this.deptRepository;
    }

    @Override
    public DataResult getTree(String name) {
        String orgCode = jwtTokenUtil.getOrgCode();
        List<Dept> deptList = deptRepository.findByLevelCodeLike(orgCode);

        return null;
    }
}
