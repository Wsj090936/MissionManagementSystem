package com.exceptions;

public class NotExcelFileException extends Exception {

    private String msg;
    public NotExcelFileException(String msg){
        this.msg = msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
