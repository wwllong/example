package com.example.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResult implements Serializable {

    private final static String OK = "ok";
    private final static String NOT_OK = "not_ok";
    private final static String SUCCESS = "成功操作";

    private String result;
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errorList;

    public static BaseResult ok(){
        return createBaseResult(OK, null, SUCCESS, null, null);
    }

    public static BaseResult ok(Object data){
        return createBaseResult(OK, data, SUCCESS, null, null);
    }

    public static BaseResult ok(Object data, Cursor cursor){
        return createBaseResult(OK, data, SUCCESS, cursor, null);
    }

    public static BaseResult notOk(List<Error> errorList){
        return createBaseResult(NOT_OK, null, null, null, errorList);
    }

    private static BaseResult createBaseResult(String result, Object data, String success, Cursor cursor, List<Error> errorList) {
        BaseResult baseResult = new BaseResult();
        baseResult.result = result;
        baseResult.data = data;
        baseResult.success = success;
        baseResult.cursor = cursor;
        baseResult.errorList = errorList;
        return baseResult;
    }

    public Boolean isSuccess(){
        return OK.equals(this.getResult());
    }

    @Data
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String msg;
    }

}
