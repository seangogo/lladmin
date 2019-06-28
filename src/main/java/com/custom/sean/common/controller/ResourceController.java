package com.custom.sean.common.controller;

import com.custom.sean.common.service.ResourceService;
import com.custom.sean.common.utils.basics.JwtTokenUtil;
import com.custom.sean.common.utils.vo.DataResult;
import com.custom.sean.common.utils.vo.StateResult;
import com.custom.sean.common.utils.vo.rbac.ResourceInfo;
import com.custom.sean.common.utils.vo.rbac.ResourceVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author seangogo
 */
@RestController
@RequestMapping("auth/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 获取资源树
     *
     * @return ResourceInfo
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('resource','role')")
    public ResourceInfo findTree(@RequestParam(required = false) String name) {
        return resourceService.getAllTree(name);
    }

    /**
     * 获取资源
     *
     * @param id 唯一标识
     * @return result
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('resource','role')")
    public DataResult findInfo(@PathVariable String id) {
        return DataResult.success(resourceService.getInfo(id));
    }

    /**
     * 获取根据角色的所有权限
     *
     * @param  id 资源账户
     * @return result
     */
    @GetMapping("/roleTree/{id}")
    @PreAuthorize("hasAuthority('role')")
    public ResourceInfo findRoleTree(@PathVariable String id) {
        return resourceService.getRoleTree(id);
    }

    /**
     * 创建资源
     *
     * @param resourceVo vo
     * @return success
     */
    @PostMapping
    @PreAuthorize("hasAuthority('resource')")
    public StateResult save(@RequestBody ResourceVo resourceVo){
        resourceService.create(resourceVo, jwtTokenUtil.getUserName());
        return StateResult.success();
    }

    /**
     * 修改资源
     *
     * @param id 唯一标识
     * @param resourceInfo vo
     * @return success
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('resource')")
    public StateResult update(@PathVariable String id,
                              @RequestBody ResourceInfo resourceInfo) {
        resourceService.update(id, resourceInfo);
        return StateResult.success();
    }

    /**
     * 删除(物理删除)
     *
     * @param id 唯一标识
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('resource')")
    public StateResult deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        return StateResult.success();
    }

    /**
     * 排序
     *
     * @param map 参数 id up
     * @return success
     */
    @PutMapping("/move")
    @PreAuthorize("hasAuthority('resource')")
    public StateResult move(@RequestBody Map<String, Object> map) {
     //   resourceService.move(map.get("id").toString(), (Boolean) map.get("up")); todo 调整
        return StateResult.success();
    }
}
