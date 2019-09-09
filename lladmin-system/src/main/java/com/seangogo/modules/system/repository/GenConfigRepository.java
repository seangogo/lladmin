package com.seangogo.modules.system.repository;

import com.seangogo.modules.system.domain.GenConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seang
 * @date 2019/8/9 15:44
 */
public interface GenConfigRepository extends JpaRepository<GenConfig,Long> {
}
