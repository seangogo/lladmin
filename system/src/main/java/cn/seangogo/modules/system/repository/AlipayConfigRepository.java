package cn.seangogo.modules.system.repository;

import cn.seangogo.modules.system.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-23
*/
public interface AlipayConfigRepository extends JpaRepository<AlipayConfig, Long>, JpaSpecificationExecutor {
}