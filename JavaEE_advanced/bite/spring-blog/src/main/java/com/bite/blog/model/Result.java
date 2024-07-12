package com.bite.blog.model;

import com.bite.blog.constants.Constant;
import lombok.Data;

@Data
public class Result {
    private int code;//200-成功   -1-失败    -2 未登录.....
    private String errMsg;
    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(Constant.SUCCESS_CODE);
        result.setErrMsg("");
        result.setData(data);
        return  result;
    }

    public static Result fail(String errMsg){
        Result result = new Result();
        result.setCode(Constant.FAIL_CODE);
        result.setErrMsg(errMsg);
        result.setData(null);
        return  result;
    }

    public static Result fail(String errMsg,Object data){
        Result result = new Result();
        result.setCode(Constant.FAIL_CODE);
        result.setErrMsg(errMsg);
        result.setData(data);
        return  result;
    }

    public static Result unlogin(String errMsg){
        Result result = new Result();
        result.setCode(Constant.UNLOGIN_CODE);
        result.setErrMsg("用户未登录");
        result.setData(null);
        return  result;
    }


}
