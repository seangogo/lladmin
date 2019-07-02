package com.custom.sean.common.repository;

import com.custom.sean.common.domain.Dict;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DictRepository extends BaseRepository<Dict, Long> {
    @Modifying
    @Transactional
    @Query("update Dict d set d.levelCode=concat(?1,d.code) where d.levelCode like CONCAT(?2,'%')")
    int updateLevelCode(String newLevelCode, String oldLevelCode);


    @Query(value = "select * from auth_dict where code=:code",nativeQuery = true)
    Dict findByCode(@Param("code") String code);
}
