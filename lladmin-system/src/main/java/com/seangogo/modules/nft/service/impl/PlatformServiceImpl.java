package com.seangogo.modules.nft.service.impl;

import com.seangogo.common.utils.QueryHelp;
import com.seangogo.common.utils.PageUtil;
import com.seangogo.common.utils.ValidationUtil;
import com.seangogo.modules.nft.domain.Platform;
import com.seangogo.modules.nft.repository.PlatformRepository;
import com.seangogo.modules.nft.service.PlatformService;
import com.seangogo.modules.nft.service.dto.PlatformDTO;
import com.seangogo.modules.nft.service.dto.PlatformQueryCriteria;
import com.seangogo.modules.nft.service.mapper.PlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import com.seangogo.modules.nft.service.mapper.PlatformMapper;
import org.springframework.data.domain.Pageable;

/**
* @author xiaojun
* @date 2022-04-27
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformRepository PlatformRepository;

    @Autowired
    private PlatformMapper PlatformMapper;

    @Override
    public Object queryAll(PlatformQueryCriteria criteria, Pageable pageable){
        Page<Platform> page = PlatformRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(PlatformMapper::toDto));
    }

    @Override
    public Object queryAll(PlatformQueryCriteria criteria){
        return PlatformMapper.toDto(PlatformRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public PlatformDTO findById(Long id) {
        Optional<Platform> Platform = PlatformRepository.findById(id);
        ValidationUtil.isNull(Platform,"Platform","id",id);
        return PlatformMapper.toDto(Platform.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlatformDTO create(Platform resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId()); 
        return PlatformMapper.toDto(PlatformRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Platform resources) {
        Optional<Platform> optionalPlatform = PlatformRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPlatform,"Platform","id",resources.getId());
        Platform Platform = optionalPlatform.get();
        Platform.copy(resources);
        PlatformRepository.save(Platform);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        PlatformRepository.deleteById(id);
    }
}