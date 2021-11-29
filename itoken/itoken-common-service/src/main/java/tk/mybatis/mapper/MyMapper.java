package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自己的 Mapper
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}