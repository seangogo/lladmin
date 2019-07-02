package com.custom.sean.common.repository;


import com.custom.sean.common.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 账户操作日志记录
 * @author sean
 * @date 2018/2/1
 */
public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {

}
