package com.igeek.mall.service;

import com.igeek.mall.dao.CategoryDao;
import com.igeek.mall.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author designal
 * @Date 2021/1/25 20:06
 */
public class CategoryService {

    private CategoryDao dao = new CategoryDao();

    //展示所有商品类别
    public List<Category> showCategoryType(){
        List<Category> list = null;
        try {
            list = dao.categoriesType();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //通过商品编号查询商品类别名称
    public String selectCategoryNameBycid(String cid){
        String typeName = "";
        try {
            typeName = dao.getTypeNameBycid(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeName;
    }

}
