package com.pressure.test.login.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @NAME: ResponseData
 * @USER: zhouyu
 * @DATE: 2020/8/1
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {

    private String msg;
    private Integer code;
    private T data;

    private ResponseData() {
    }

    private ResponseData(Integer code,String msg) {
        this.msg = msg;
        this.code = code;
    }

    private ResponseData(Integer code,String msg, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Boolean isSuccessful(){
        return this.code.equals(ResponseEnum.SUCCESS.getCode());
    }

    public static <T>ResponseData<T> creatByErrorMsg(String msg){
        return new ResponseData<T>(ResponseEnum.ERROR.getCode(),msg);
    }
    public static <T>ResponseData<T> creatBySuccessMsg(String msg){
        return new ResponseData<T>(ResponseEnum.SUCCESS.getCode(),msg);
    }
    public static <T>ResponseData<T> creatBySuccessMsg(T data,String msg){
        return new ResponseData<T>(ResponseEnum.SUCCESS.getCode(),msg,data);
    }


//    public static <T>ResponseData<T> creatByError



}
