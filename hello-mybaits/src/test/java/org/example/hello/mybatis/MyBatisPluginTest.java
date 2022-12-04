package org.example.hello.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.hello.mybatis.entity.Order;
import org.example.hello.mybatis.entity.User;
import org.example.hello.mybatis.mapper.OrderMapper;
import org.example.hello.mybatis.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jack.wen
 * @version 1.0.0
 */
public class MyBatisPluginTest {

    private UserMapper userMapper;
    private SqlSession sqlSession;

    private OrderMapper orderMapper;

    @Before
    public void before() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @After
    public void after() {
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        // 执⾏sql语句
        List<User> userList = userMapper.findAll();
        // 打印结果
        System.out.println(userList);
    }

    @Test
    public void testPageHelper() {
        // 设置分⻚参数
        PageHelper.startPage(1, 2);
        List<User> select = userMapper.findAll();

        for (User user : select) {
            System.out.println(user);
        }
        // 其他分⻚的数据
        PageInfo<User> pageInfo = new PageInfo<User>(select);
        System.out.println("总条数:" + pageInfo.getTotal());
        System.out.println("总⻚数:" + pageInfo. getPages ());
        System.out.println("当前⻚:" + pageInfo. getPageNum());
        System.out.println("每⻚显万⻓度:" + pageInfo.getPageSize());
        System.out.println("是否第一⻚:"+ pageInfo.isIsFirstPage());
        System.out.println("是否最后一⻚:" + pageInfo.isIsLastPage());
    }

    @Test
    public void testTk() {
        User user = new User();
        user.setId(4);
        // 1. mapper基础接口
        // 1.1 select 接口, 查询条件都是等号
        User user1 = userMapper.selectOne(user); // 根据实体中的属性进行查询，只能有—个返回值
        List<User> users = userMapper.select(null); // 查询全部结果
        userMapper.selectByPrimaryKey(1); // 根据主键查询
        userMapper.selectCount(user); // 根据实体中的属性查询总数
        // 1.2 insert 接口
        int insert = userMapper.insert(user); // 保存实体，null值也会保存，不会使用数据库默认值
        int insert2 = userMapper.insertSelective(user); // 保存实体，null的属性不会保存，会使用数据库默认值
        // 1.3 update 接口
        int update1 = userMapper.updateByPrimaryKey(user); // 根据主键更新实体全部字段， null值会被更新
        // 1.3 delete 接口
        int delete = userMapper.delete(user); // 根据实体属性作为条件进行删除，查询条件使用等号
        userMapper.deleteByPrimaryKey(4); // 根据主键字段进行删除

        // 2. example方法
        // 2.1 自定义查询
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", 1);
        example.createCriteria().andLike("username", "jack");
        List<User> users1 = userMapper.selectByExample(example);
    }

}
