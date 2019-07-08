package com.seangogo.modules.system.rest;

import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.service.ResourceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author seangogo
 */
@RestController
@RequestMapping("resource")
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
    public DataResult findTree(@RequestParam(required = false) String name) {
        return DataResult.success(resourceService.getAllTree(name));
    }

    /**
     * 获取资源
     *
     * @param id 唯一标识
     * @return result
     */
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('resource','role')")
//    public DataResult findInfo(@PathVariable Long id) {
//        return DataResult.success(resourceService.getInfo(id));
//    }
//
//    /**
//     * 获取根据角色的所有权限
//     *
//     * @param  id 资源账户
//     * @return result
//     */
//    @GetMapping("/roleTree/{id}")
//    @PreAuthorize("hasAuthority('role')")
//    public ResourceInfo findRoleTree(@PathVariable Long id) {
//        return resourceService.getRoleTree(id);
//    }
//
//    /**
//     * 创建资源
//     *
//     * @param resourceVo vo
//     * @return success
//     */
//    @PostMapping
//    @PreAuthorize("hasAuthority('resource')")
//    public StateResult save(@RequestBody ResourceVo resourceVo){
//        resourceService.create(resourceVo, jwtTokenUtil.getUserName());
//        return StateResult.success();
//    }
//
//    /**
//     * 修改资源
//     *
//     * @param id 唯一标识
//     * @param resourceInfo vo
//     * @return success
//     */
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('resource')")
//    public StateResult update(@PathVariable Long id,
//                              @RequestBody ResourceInfo resourceInfo) {
//        resourceService.update(id, resourceInfo);
//        return StateResult.success();
//    }
//
//    /**
//     * 删除(物理删除)
//     *
//     * @param id 唯一标识
//     */
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('resource')")
//    public StateResult deleteResource(@PathVariable Long id) {
//        resourceService.deleteResource(id);
//        return StateResult.success();
//    }
//
//    /**
//     * 排序
//     *
//     * @param map 参数 id up
//     * @return success
//     */
//    @PutMapping("/move")
//    @PreAuthorize("hasAuthority('resource')")
//    public StateResult move(@RequestBody Map<String, Object> map) {
//     //   resourceService.move(map.get("id").toString(), (Boolean) map.get("up")); todo 调整
//        return StateResult.success();
//    }
}
