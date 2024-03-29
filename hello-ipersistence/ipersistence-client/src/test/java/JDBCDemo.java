import com.example.hello.ipersistence.pojo.entity.User;

import java.sql.*;

/**
 * JDBC操作数据数据库的问题
 */
public class JDBCDemo {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8",
                    "root",
                    "123456");
            // 定义sql语句 ？表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第⼀个参数为sql语句中参数(?)的序号(从1开始)，第⼆个参数为设置的参数值
            preparedStatement.setString(1, "jack");
            // 向数据库发出sql执⾏查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            // 遍历查询结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                // 封装User
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace(); } }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
