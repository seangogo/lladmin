package cn.seangogo.modules.system.rest;

import cn.seangogo.modules.system.domain.Dept;
import cn.seangogo.modules.system.service.DeptService;
import cn.seangogo.modules.system.service.dto.DeptDTO;
import cn.seangogo.modules.system.service.query.DeptQueryService;
import cn.seangogo.aop.log.Log;
import cn.seangogo.config.DataScope;
import cn.seangogo.exception.BadRequestException;
import cn.seangogo.modules.system.domain.Dept;
import cn.seangogo.modules.system.service.DeptService;
import cn.seangogo.modules.system.service.dto.DeptDTO;
import cn.seangogo.modules.system.service.query.DeptQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
* @author jie
* @date 2019-03-25
*/
@RestController
@RequestMapping("api")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DeptQueryService deptQueryService;

    @Autowired
    private DataScope dataScope;

    private static final String ENTITY_NAME = "dept";

    @Log("查询部门")
    @GetMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDepts(DeptDTO resources){
        // 数据权限
        Set<Long> deptIds = dataScope.getDeptIds();
        List<DeptDTO> deptDTOS = deptQueryService.queryAll(resources, deptIds);
        return new ResponseEntity(deptService.buildTree(deptDTOS),HttpStatus.OK);
    }

    @Log("新增部门")
    @PostMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Dept resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(deptService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改部门")
    @PutMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_EDIT')")
    public ResponseEntity update(@Validated(Dept.Update.class) @RequestBody Dept resources){
        deptService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除部门")
    @DeleteMapping(value = "/dept/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        deptService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}