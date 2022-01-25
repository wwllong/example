package com.example.itoken.common.ctrl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.itoken.common.domain.BaseDomain;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.common.dto.BaseResultPlus;
import com.example.itoken.common.service.BaseService;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 控制层实现基类
 */
public abstract class BaseController<S extends BaseService<T>, T extends BaseDomain> {

    @Autowired
    protected S baseService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param domain
     * @return
     */
    @PostMapping("page/{pageNum}/{pageSize}")
    public abstract BaseResult<List<T>> page(@PathVariable(value = "pageNum") Integer pageNum,
                                            @PathVariable(value = "pageSize") Integer pageSize,
                                            @RequestBody T domain);

    protected BaseResult<List<T>> page(Integer pageNum, Integer pageSize, Example example) {
        return BaseResultPlus.ok(baseService.page(pageNum, pageSize, example));
    }

    /**
     * PrimaryKey查询
     * @return
     */
    @GetMapping("{id}")
    public BaseResult<T> get(@PathVariable(value = "id") Object id) {
        return BaseResult.ok(baseService.findByPrimaryKey(id));
    }

    /**
     * PrimaryKey删除
     * @return
     */
    @DeleteMapping("{id}")
    public BaseResult<Integer> delete(@PathVariable(value = "id") String id){
        return BaseResult.ok(this.deletes(CollUtil.newArrayList(id)).getData());
    }

    /**
     * PrimaryKey批量删除
     * @return
     */
    @DeleteMapping("batch")
    public BaseResult<Integer> deletes(@RequestParam(value = "ids[]") List<String> ids){
        if (CollUtil.isEmpty(ids)) {
            return BaseResult.ok(0);
        }
        HashSet<Object> objects = CollUtil.newHashSet();
        objects.addAll(ids);
        return BaseResult.ok(baseService.deleteByPrimaryKeys(objects));
    }

    @PostMapping()
    public BaseResult<Integer> save(@RequestBody T domain) {
        return BaseResult.ok(baseService.save(domain));
    }

}
