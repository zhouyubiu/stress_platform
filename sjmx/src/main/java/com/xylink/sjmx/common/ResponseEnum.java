package com.xylink.sjmx.common;

/**
 * @NAME: ResponseEnum
 * @USER: zhouyu
 * @DATE: 2020/8/1
 */
public enum ResponseEnum  {

    SUCCESS(0,"success"),
    ERROR(-1,"error");

    private final Integer code;
    private final String desc;


    ResponseEnum(Integer code,String desc) {
        this.desc = desc;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
