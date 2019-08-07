package com.seangogo.modules.system.service.dto;

import lombok.*;

/**
 * 分页查询参数集合类
 */
public class PageQueryDto {

    /**
     * 品牌
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserQueryDto {
        private String realName;
        private String email;
        private String phone;
        private String username;
        private String orgCode;
        private Long roleId;
    }
}
