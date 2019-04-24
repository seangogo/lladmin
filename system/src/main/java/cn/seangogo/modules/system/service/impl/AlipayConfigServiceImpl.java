package cn.seangogo.modules.system.service.impl;

import cn.seangogo.modules.system.repository.AlipayConfigRepository;
import cn.seangogo.modules.system.service.AlipayConfigService;
import cn.seangogo.modules.system.service.mapper.AlipayConfigMapper;
import cn.seangogo.modules.system.domain.AlipayConfig;
import cn.seangogo.utils.ValidationUtil;
import cn.seangogo.modules.system.repository.AlipayConfigRepository;
import cn.seangogo.modules.system.service.AlipayConfigService;
import cn.seangogo.modules.system.service.dto.AlipayConfigDTO;
import cn.seangogo.modules.system.service.mapper.AlipayConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-23
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlipayConfigServiceImpl implements AlipayConfigService {

    @Autowired
    private AlipayConfigRepository alipayConfigRepository;

    @Autowired
    private AlipayConfigMapper alipayConfigMapper;

    @Override
    public AlipayConfigDTO findById(Long id) {
        Optional<AlipayConfig> alipayConfig = alipayConfigRepository.findById(id);
        ValidationUtil.isNull(alipayConfig,"AlipayConfig","id",id);
        return alipayConfigMapper.toDto(alipayConfig.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AlipayConfigDTO create(AlipayConfig resources) {
        return alipayConfigMapper.toDto(alipayConfigRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AlipayConfig resources) {
        Optional<AlipayConfig> optionalAlipayConfig = alipayConfigRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAlipayConfig,"AlipayConfig","id",resources.getId());

        AlipayConfig alipayConfig = optionalAlipayConfig.get();
        // 此处需自己修改
        resources.setId(alipayConfig.getId());
        alipayConfigRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        alipayConfigRepository.deleteById(id);
    }
}