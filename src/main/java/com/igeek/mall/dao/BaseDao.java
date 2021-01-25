package com.igeek.mall.dao;

import com.igeek.mall.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author designal
 * @Date 2021/1/22 13:47
 */
public class BaseDao<T> {

    private QueryRunner runner = new QueryRunner();

    //增删改
    public int update(String sql, Object...params) throws SQLException {
        return runner.update(DataSourceUtils.getConnection(),sql,params);
    }

    //查询单个值
    public Object getSingleValue(String sql, Class<T> clazz, Object...params) throws SQLException {
        return  runner.query(DataSourceUtils.getConnection(),sql,new ScalarHandler<>(), params);
    }

    //查询当个对象
    public T getBean(String sql, Class<T> clazz, Object...params) throws SQLException {
        return runner.query(DataSourceUtils.getConnection(),sql,new BeanHandler<>(clazz),params);
    }

    //查询多个对象
    public List<T> getBeanList(String sql, Class<T> clazz, Object...params) throws SQLException {
        return runner.query(DataSourceUtils.getConnection(),sql, new BeanListHandler<>(clazz), params);
    }


}
