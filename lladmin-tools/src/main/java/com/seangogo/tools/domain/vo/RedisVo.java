package com.seangogo.tools.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author seang
 * @date 2019/7/3 11:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisVo implements Serializable {

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
