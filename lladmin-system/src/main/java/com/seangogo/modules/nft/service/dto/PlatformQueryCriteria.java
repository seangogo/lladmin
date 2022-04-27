package com.seangogo.modules.nft.service.dto;

import com.seangogo.common.rest.Query;
import lombok.Data;


/**
* @author xiaojun
* @date 2022-04-27
*/
@Data
public class PlatformQueryCriteria{

    private String wechatName;

    private String name;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private Boolean business;
}