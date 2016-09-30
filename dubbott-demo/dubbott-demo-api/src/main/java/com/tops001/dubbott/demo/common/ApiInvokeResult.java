/*******************************************************************************
 * Copyright (c) 2016 Tops Tech Corp.
 *
 *
 * This software is the confidential and proprietary information of
 * Tops Tech Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with tops001.com. 
 * *******************************************************************************/
package com.tops001.dubbott.demo.common;

import java.io.Serializable;

/**
 * @author root on Jul 28, 2016 1:49:24 PM
 */
public class ApiInvokeResult<T> implements Serializable {

    private static final long serialVersionUID = 6302748315519313824L;

    private ResultCode        resultCode;
    private String            message;
    private T                 value;

    public ApiInvokeResult(){
    }

    public ApiInvokeResult(T value){
        this(ResultCode.SUCCESS, null, value);
    }

    public ApiInvokeResult(ResultCode resultCode, String message){
        this(resultCode, message, null);
    }

    public ApiInvokeResult(ResultCode resultCode, String message, T value){
        this.resultCode = resultCode;
        this.message = message;
        this.value = value;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static <T> ApiInvokeResult<T> buildSuccesResult(T value) {
        return new ApiInvokeResult<T>(ResultCode.SUCCESS, null, value);
    }

    public static <T> ApiInvokeResult<T> buildFailResult(ResultCode resultCode, String message) {
        return new ApiInvokeResult<T>(resultCode, message);
    }

    @Override
    public String toString() {
        return String.format("Result [resultCode=%s, message=%s, value=%s]", resultCode, message, value);
    }
}
