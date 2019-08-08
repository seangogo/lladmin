package com.seangogo.modules.system.service.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seang
 * @date 2019/8/7 17:40
 */
@Getter
@Setter
public class DataBaseTreeDto {

    private Long id;

    private String label;

    private List<DataBaseTreeDto> children = new ArrayList<>();

    public DataBaseTreeDto(Long id, String label, List<DataBaseTreeDto> children) {
        this.id = id;
        this.label = label;
        this.children = children;
    }

    public DataBaseTreeDto(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public DataBaseTreeDto() {
    }
}
