package com.igeek.mall.dao;

import com.igeek.mall.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 商品类别数据层
 * @Author designal
 * @Date 2021/1/25 20:02
 */
public class CategoryDao extends BaseDao<Category> {

    //展示所有商品类别
    public List<Category> categoriesType() throws SQLException {
        String sql = "select * from category";
        return this.getBeanList(sql,Category.class);
    }

    //通过商品编号获取类别名称
    public String getTypeNameBycid(String cid) throws SQLException {
        String sql = "select cname from category where cid = ? ";
        Object o = this.getSingleValue(sql, Category.class, cid);
        return o.toString();
    }
}
