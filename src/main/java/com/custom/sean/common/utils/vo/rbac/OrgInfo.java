package com.custom.sean.common.utils.vo.rbac;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author sean
 * @date 2018/1/9
 */
@Setter
@Getter
public class OrgInfo {

    private String id;

    private String name;

    private String value;

    private String label;

    private String code;

    private String levelCode;

    private String parentId;

    private int orgType;

    private String remark;

    private List<String> brandIds;

    private List<OrgInfo> children;
}
