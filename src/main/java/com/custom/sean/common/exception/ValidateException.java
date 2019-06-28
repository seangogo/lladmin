/**
 * 
 */
package com.custom.sean.common.exception;


/**
 * vo 参数验证类
 * @author seangogo
 */

public class ValidateException extends RuntimeException {

	private String code;


	public ValidateException(String code, String message) {
		super(message);
		this.code = code;
	}
}
