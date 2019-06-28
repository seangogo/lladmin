package com.custom.sean.common.utils.vo.rbac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 *
 * @author sean
 * @date 2018/3/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailRoleVo {
    private String name;
    private Set<String> resourceName;
}
