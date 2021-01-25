package com.igeek.mall.service;

import com.igeek.mall.dao.ProductDao;
import com.igeek.mall.entity.Product;
import com.igeek.mall.vo.PageVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author designal
 * @Date 2021/1/25 19:35
 */
public class ProductService {

    private ProductDao dao = new ProductDao();

    //展示热门商品
    public List<Product> showHot(){
        List<Product> list = null;
        try {
            list = dao.hotProductList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //展示最新商品
    public List<Product> showNew(){
        List<Product> list = null;
        try {
            list = dao.newProductList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //条件搜索（cid + pname） + 分页查询
    public PageVo<Product> viewProductsByCidPname(String cid,String pname, int pageNow){
        PageVo pageVo = null;

        try {
            //总记录数
            int counts = dao.selectCounts(cid,pname);
            //总页数
            int myPages = counts%12==0? counts/12: (int) Math.ceil(counts / 12.0);
            //起始条目数
            int begin = (pageNow-1)*12;

            List<Product> productList = dao.productListBycidpname(cid, pname, begin);

            pageVo = new PageVo(cid,pname,pageNow,myPages,productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageVo;
    }
}
