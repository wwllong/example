package com.example.itoken.common.web.components;

import com.example.itoken.common.dto.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataTablesResult extends BaseResult implements Serializable {

    private static final long serialVersionUID = -699229442512093295L;

    /**
     * DT（datatable）绘制计数器
     * */
    private int draw;
    /**
     * 数据库里总共记录数
     */
    private int recordsTotal;
    /**
     * 过滤后的记录数
     */
    private int recordsFiltered;

    /**
     * 错误提示
     */
    private String error;


}