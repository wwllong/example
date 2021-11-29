package com.example.itoken.common.constants;

import com.example.itoken.common.dto.BaseResult;
import com.google.common.collect.Lists;
import lombok.Getter;

@Getter
public enum EnumHttpStatus {

    BAD_GATEWAY(502, "从上游服务器收到无效的响应");

    private final Integer status;
    private final String content;

    EnumHttpStatus(Integer status, String content){
        this.status = status;
        this.content = content;
    }

    public static BaseResult toBaseResult(EnumHttpStatus enumHttpStatus){
        return BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error(String.valueOf(enumHttpStatus.getStatus()), enumHttpStatus.getContent()))
        );
    }

}
