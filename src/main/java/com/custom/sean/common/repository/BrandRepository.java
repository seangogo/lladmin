package com.custom.sean.common.repository;


import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
public interface BrandRepository extends BaseRepository<Brand,Long> {
    /**
     * 根据编码查找
     * @param code 编码
     * @return ele
     */
    Brand findByCode(String code);
    /**
     * 查询所有品牌的名称和编码
     * @return
     */
    @Query("select new map(b.id as value, b.name as label) from Brand b")
    List<Map<String,String>> findLabels();

    @Query("select b from Brand b fetch all properties ")
    List<Brand> findTree();
}
