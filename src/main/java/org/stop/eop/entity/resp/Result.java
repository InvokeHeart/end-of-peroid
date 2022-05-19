package org.stop.eop.entity.resp;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static Result<String> ok() {
        Result<String> result = new Result<>();
        result.setData("");
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getCodeDesc());
        return result;
    }

    public static Result<String> ok(String msg) {
        Result<String> result = new Result<>();
        result.setData("");
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(msg);
        return result;
    }

    public static <T> Result<T> ok(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getCodeDesc());
        return result;
    }

    public static Result<String> error() {
        Result<String> result = new Result<>();
        result.setData("");
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getCodeDesc());
        return result;
    }

    public static Result<String> error(String message) {
        Result<String> result = new Result<>();
        result.setData("");
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(message);
        return result;
    }

    public static Result<String> noAuth() {
        Result<String> result = new Result<>();
        result.setData("");
        result.setCode(ResultCode.UnAUTH.getCode());
        result.setMessage(ResultCode.UnAUTH.getCodeDesc());
        return result;
    }


}
