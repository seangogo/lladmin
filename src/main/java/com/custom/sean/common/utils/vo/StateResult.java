package com.custom.sean.common.utils.vo;

/**
 *
 * @author sean
 * @date 2017/11/7
 */

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sean
 * @date 2017/10/20
 */
@Getter
@Setter
public class StateResult {
    protected String statusCode;
    protected String statusMessage;

    public static StateResult success() {
        return  new StateResult(ResultEnum.SUCCESS);
    }

    public static StateResult error(ResultEnum resultEnum) {
        return new StateResult(resultEnum);
    }


    public StateResult(String code, String description) {
        this.statusCode = code;
        this.statusMessage = description;
    }
    public StateResult(ResultEnum resultEnum) {
        this.statusCode = resultEnum.getCode();
        this.statusMessage = resultEnum.getMessage();
    }
    public StateResult() {
    }
}
