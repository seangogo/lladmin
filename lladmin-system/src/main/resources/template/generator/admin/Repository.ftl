package ${package}.repository;

import ${package}.domain.${className};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author ${author}
* @date ${date}
*/
public interface ${className}Repository extends JpaRepository<${className}, ${pkColumnType}>, JpaSpecificationExecutor {
<#if columns??>
    <#list columns as column>
        <#if column.columnKey = 'UNI'>

    /**
     * findBy${column.capitalColumnName}
     * @param ${column.columnName}
     * @return
     */
    ${className} findBy${column.capitalColumnName}(${column.columnType} ${column.columnName});
        </#if>
    </#list>
</#if>

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ${className})) {
            return false;
        }
        return super.getId() != null && super.getId().equals(((${className}) obj).getId());
    }

    @Override
    public int hashCode() {
        return 2023;
    }
}