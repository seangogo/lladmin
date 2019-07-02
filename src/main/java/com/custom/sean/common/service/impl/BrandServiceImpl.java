package com.custom.sean.common.service.impl;

import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.repository.BrandRepository;
import com.custom.sean.common.repository.ProjectRepository;
import com.custom.sean.common.service.BrandService;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sean
 * @date 2018/2/6
 */
@Service
@Transactional
public class BrandServiceImpl  extends BaseServiceImpl<Brand, Long> implements BrandService {

    @Resource
    private BrandRepository brandRepository;

    @Resource
    private ProjectRepository projectRepository;

    @Override
    public BaseRepository<Brand, Long> getBaseDao() {
        return this.brandRepository;
    }

    @Override
    public List<Map<String,String>> findLabels() {
        return brandRepository.findLabels();
    }
}
