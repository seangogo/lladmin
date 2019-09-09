package ${package}.domain;

import lombok.Getter;
import lombok.Setter;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
<#if hasTimestamp>
import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import com.seangogo.base.jpa.BaseEntity;
import java.io.Serializable;

/**
* @author ${author}
* @date ${date}
*/
@Entity
@Getter
@Setter
@Table(name="${tableName}")
public class ${className} extends BaseEntity<String> implements Serializable {

<#if columns??>
    <#list columns as column>

    <#if column.columnComment != ''>
    // ${column.columnComment}
    </#if>
    <#if column.columnKey = 'PRI'>
    @Id
    <#if auto>
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    </#if>
    @Column(name = "${column.columnName}"<#if column.columnKey = 'UNI'>,unique = true</#if><#if column.isNullable = 'NO' && column.columnKey != 'PRI'>,nullable = false</#if>)
    private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>

    public void copy(${className} source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}