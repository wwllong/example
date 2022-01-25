package com.example.itoken.common.web.ctrl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.domain.BaseDomain;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.common.web.components.DataTablesResult;
import com.example.itoken.service.api.base.BaseServiceClient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Web基础控制层
 * @param <C>
 * @param <T>
 */
public abstract class BaseController<C extends BaseServiceClient<T>, T extends BaseDomain>{

    @Autowired
    protected C baseClient;

    /**
     * ModelAttribute-创建实体对象
     */
    public abstract T createEntity();

    /**
     * 回显数据
     */
    @ModelAttribute
    public T getEntity(String id){
        return id != null ? baseClient.get(id).getData() : createEntity();
    }

    /**
     * 跳转内容列表
     */
    @GetMapping(value = "list")
    public String list(){
        return "list";
    }

    /**
     * 跳转新增页面
     */
    @GetMapping(value = "form")
    public String form(){
        return "form";
    }

    /**
     * 跳转详情页面
     */
    @GetMapping(value = "detail")
    public String detail(){
        return "detail";
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @GetMapping(value = "page")
    public DataTablesResult page(HttpServletRequest req, T entity){

        String drawStr = req.getParameter("draw");
        String startStr = req.getParameter("start");
        String lengthStr = req.getParameter("length");

        int draw = drawStr == null ? 0 : Integer.parseInt(drawStr);
        int length = lengthStr == null ? 0 : Integer.parseInt(lengthStr);
        int start = startStr == null ? 0 : (Integer.parseInt(startStr) * 2) / length;

        DataTablesResult dataTablesResult = new DataTablesResult();
        BaseResult<List<T>> page = baseClient.page(start, length, entity);
        dataTablesResult.setDraw(draw);
        dataTablesResult.setRecordsTotal(page.getCursor().getTotal());
        dataTablesResult.setRecordsFiltered(page.getCursor().getTotal());
        dataTablesResult.setData(page.getData());
        return dataTablesResult;

    }

    @PostMapping(value = "delete")
    @ResponseBody
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StrUtil.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            baseClient.deletes(CollUtil.newArrayList(idArray));
            baseResult = BaseResult.ok("删除成功");
        }else{
            baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("删除失败", "删除失败")));
        }
        return baseResult;
    }

}
