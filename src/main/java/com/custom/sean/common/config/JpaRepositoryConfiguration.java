package com.custom.sean.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @author seang
 */
@Configuration
@EnableJpaRepositories(
		basePackages={"com.custom.sean.common.repository"})
@EnableSpringDataWebSupport
public class JpaRepositoryConfiguration {

}
