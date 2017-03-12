package com.dongql.deploy.common;

/**
 * Created by dongqilin on 2017/3/13.
 */
public class Result {

    private int code;
    private String message;

    public Result() {
    }

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Result success() {
        return new Result(0, "成功");
    }

    public static Result error(String message) {
        return new Result(1, message);
    }

}
