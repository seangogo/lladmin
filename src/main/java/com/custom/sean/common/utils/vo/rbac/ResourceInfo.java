package com.custom.sean.common.utils.vo.rbac;


import com.custom.sean.common.utils.menu.ResourceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sean
 *
 */
@Setter
@Getter
public class ResourceInfo {

	/**
	 * 资源id
	 */
	private String id;

	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源编码
	 */
	private String code;

	/**
	 * 层级编码
	 */
	private String levelCode;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 资源类型 0=菜单 1=按钮 2=路由
	 */
	private ResourceType type;

	/**
	 * 详情
	 */
	private String remark;

	/**
	 * 父资源
	 */
	private String parentId;

	/**
	 * 创建人
	 */
	private String createdBy;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createdDate;
	/**
	 * 子节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

}
