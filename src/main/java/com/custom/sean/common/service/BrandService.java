package com.custom.sean.common.service;

import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.utils.jpa.BaseService;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sean
 * @date 2018/2/6
 */
public interface BrandService extends BaseService<Brand,String>{
    List<Map<String,String>> findLabels();
}
