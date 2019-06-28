package com.custom.sean.common.utils.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author sean
 * @date 2017/10/20
 */
@Getter
@Setter
@NoArgsConstructor
public class DataResult<T> {
    protected String statusCode;
    protected String statusMessage;

    private T data;

    public static DataResult success(Object object) {
        return  new DataResult(ResultEnum.SUCCESS,object);
    }

    public DataResult(ResultEnum resultEnum, T data) {
        this.statusCode = resultEnum.getCode();
        this.statusMessage = resultEnum.getMessage();
        this.data = data;
    }
}
