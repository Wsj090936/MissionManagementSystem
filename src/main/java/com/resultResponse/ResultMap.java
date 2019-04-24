package com.resultResponse;

public class ResultMap {
    private String code;
    private String desc;

    private ResultMap(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
    public static ResultMap getMsg(String code,String  desc){
        return new ResultMap(code,desc);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
