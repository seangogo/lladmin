package com.seangogo.modules.nft.repository;


import com.seangogo.modules.nft.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author xiaojun
* @date 2022-04-27
*/
public interface PlatformRepository extends JpaRepository<Platform, Long>, JpaSpecificationExecutor {
}