package com.example.itoken.common.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseResultPlus<T> extends BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -927290696015775404L;

    public static <T> BaseResult<List<T>> ok(PageInfo<T> page){
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(Long.valueOf(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());
        return BaseResult.ok(page.getList(), cursor);
    }

}
