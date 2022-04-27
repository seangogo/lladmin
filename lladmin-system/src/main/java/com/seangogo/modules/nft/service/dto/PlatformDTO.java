package com.seangogo.modules.nft.service.dto;

import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
* @author xiaojun
* @date 2022-04-27
*/
@Data
public class PlatformDTO implements Serializable {

    private String wechatName;

    private String name;

    private Boolean business;

    private String remark;

    private String url;

    // 处理精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
}