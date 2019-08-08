package com.seangogo.common.utils;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * react 分页返回对象
 *
 * @author sean
 * @date 2017/11/16
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> { //todo 重构

    /**
     * 分页数据
     **/
    List<T> list;

    /**
     * 分页构造器
     **/
    Pagination pagination;

    public static PageResult getPageVo(Page<?> page) {
        PageResult pageResult = new PageResult();
        pageResult.setList(page.getContent());
        pageResult.setPagination(new Pagination(page.getNumber() + 1, page.getSize(), page.getTotalElements()));
        return pageResult;
    }

    public static PageResult getPageVo(List list, Page page) {
        PageResult pageResult = new PageResult();
        pageResult.setList(list);
        pageResult.setPagination(new Pagination(page.getNumber() + 1, page.getSize(), page.getTotalElements()));
        return pageResult;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Pagination {
        private int current;
        private int pageSize;
        private long total;
    }
}
