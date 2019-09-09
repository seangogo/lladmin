package com.seangogo.modules.system.service.dto;

import lombok.*;

/**
 * 分页查询参数集合类
 * @author seang
 */
public class PageQueryDTO {

    /**
     * 用户
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

    /**
     * 数据库表
     */
    @Data
    public static class TableQueryDto {
        private String tableName;
        private Long dataBaseId;
    }
}
