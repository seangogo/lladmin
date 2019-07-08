package com.seangogo.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Data
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private Boolean locked;

    @JsonIgnore
    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    private Set<RoleSimpleDTO> roles;

    private JobSimpleDTO job;

    private DeptSimpleDTO dept;

    private Long deptId;
}
