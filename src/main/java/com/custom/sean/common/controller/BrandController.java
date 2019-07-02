package com.custom.sean.common.controller;

import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.service.BrandService;
import com.custom.sean.common.utils.jpa.BaseController;
import com.custom.sean.common.utils.vo.StateResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author sean
 * @date 2018/2/6
 */
@RestController
@RequestMapping("/auth/brand")
public class BrandController extends BaseController {

    @Resource
    private BrandService brandService;

    /**
     * 品牌select查询
     *
     * @return labels
     */
    @GetMapping(value = "/labels")
    public List<Map<String, String>> searchLabel() {
        return brandService.findLabels();
    }

    /**
     * 列表查询
     *
     * @return list
     */
    @GetMapping(value = "/list")
    public List<Brand> search() {
        return brandService.findAll();
    }


    /**
     * 新增品牌
     *
     * @param brand  品牌
     * @return success
     */
    @PostMapping(value = "/add")
    public StateResult add(@RequestBody Brand brand) {
        brandService.save(brand);
        return StateResult.success();
    }

    /**
     * 删除品牌
     *
     * @param id 主键
     * @return success
     */
    @DeleteMapping(value = "/delete/{id}")
    public StateResult delete(@PathVariable Long id) {
        brandService.delete(id);
        return StateResult.success();
    }
}
