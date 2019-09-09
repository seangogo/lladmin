package com.seangogo.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.modules.system.domain.DataBase;

/**
 * @author seang
 * @date 2019/8/8 15:16
 */
public interface FieldInfoDto {

    public Long getId();

    /** 数据库字段名称 **/
    public String getColumnName();

    /** 允许空值 **/
    public boolean isIsNullable();

    /** 数据库字段键类型 **/
    public String getFieldType();

    /** 数据库字段注释 **/
    public String getColumnComment();

    /** 数据库字段键类型 **/
    public String getColumnKey();

    /** 额外的参数 **/
   public String getExtra();

   /** 查询 1:模糊 2：精确 **/
   public String getColumnQuery();

   /** 是否在列表显示 **/
   public String getColumnShow();
}
