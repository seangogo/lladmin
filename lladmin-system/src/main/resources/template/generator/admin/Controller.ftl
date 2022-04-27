package ${package}.rest;

import ${package}.domain.${className};
import ${package}.service.${className}Service;
import ${package}.service.dto.${className}QueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("api")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${changeClassName}Service;

    @GetMapping(value = "/${changeClassName}")
    @PreAuthorize("hasAnyRole('cjyly','${upperCaseClassName}_ALL','${upperCaseClassName}_SELECT')")
    public ResponseEntity get${className}s(${className}QueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(${changeClassName}Service.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping(value = "/${changeClassName}")
    @PreAuthorize("hasAnyRole('cjyly','${upperCaseClassName}_ALL','${upperCaseClassName}_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ${className} resources){
        return new ResponseEntity(${changeClassName}Service.create(resources),HttpStatus.CREATED);
    }

    @PutMapping(value = "/${changeClassName}")
    @PreAuthorize("hasAnyRole('cjyly','${upperCaseClassName}_ALL','${upperCaseClassName}_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ${className} resources){
        ${changeClassName}Service.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/${changeClassName}/{${pkChangeColName}}")
    @PreAuthorize("hasAnyRole('cjyly','${upperCaseClassName}_ALL','${upperCaseClassName}_DELETE')")
    public ResponseEntity delete(@PathVariable ${pkColumnType} ${pkChangeColName}){
        ${changeClassName}Service.delete(${pkChangeColName});
        return new ResponseEntity(HttpStatus.OK);
    }
}