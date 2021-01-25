package com.igeek.mall.service;

import com.igeek.mall.dao.UserDao;
import com.igeek.mall.entity.User;
import com.igeek.mall.utils.DataSourceUtils;

import java.sql.SQLException;

/**
 * @Description TODO
 * @Author designal
 * @Date 2021/1/23 10:25
 */
public class UserService {

    private UserDao dao = new UserDao();


    //注册
    public boolean register(User user){
        try {
            int i = dao.insert(user);
            if(i>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //激活
    public boolean activeState(String code){
        try {
            return dao.updateState(code)>0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //根据昵称校验
    public boolean validate(String username){
        try {
            Long one = dao.selectOne(username);
            //true 当前用户存在 false 当前用户不存在
            return one>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
