package com.seangogo.common.utils;

/**
 * @author sean
 * @date 2017/11/7
 */

import com.seangogo.common.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sean
 * @date 2017/10/20
 */
@Getter
@Setter
public class StateResult {
    protected String statusCode;
    protected String statusMessage;

    public static StateResult success() {
        return new StateResult(ResultEnum.SUCCESS);
    }

    public static StateResult error(ResultEnum resultEnum) {
        return new StateResult(resultEnum);
    }

    public static StateResult error(Exception e) {
        return new StateResult("1", e.getMessage());
    }


    private StateResult(String code, String description) {
        this.statusCode = code;
        this.statusMessage = description;
    }

    private StateResult(ResultEnum resultEnum) {
        this.statusCode = resultEnum.getCode();
        this.statusMessage = resultEnum.getMessage();
    }

    public StateResult() {
    }
}
