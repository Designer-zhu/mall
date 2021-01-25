package com.igeek.mall.dao;

import com.igeek.mall.entity.User;

import java.sql.SQLException;

/**
 * @Description TODO
 * @Author designal
 * @Date 2021/1/22 13:56
 */
public class UserDao extends BaseDao<User> {

    //插入用户
    public int insert(User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,0,?,?)";
        return this.update(sql,user.getUid(),user.getUsername(),user.getPassword(),
                user.getName(),user.getEmail(),user.getTelephone(),
                user.getBirthday(),user.getSex(),user.getCode(),user.getAddress());
    }

    //更新状态
    public int updateState(String code) throws SQLException {
        String sql = "update user set state = ? where code = ?";
        return this.update(sql, 1, code);
    }

    //通过用户名查询用户
    public Long selectOne(String username) throws SQLException {
        String sql = "select count(*) from user where username = ?";
        return (Long)this.getSingleValue(sql, User.class, username);
    }
}