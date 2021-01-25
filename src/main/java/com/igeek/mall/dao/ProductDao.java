package com.igeek.mall.dao;

import com.igeek.mall.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author designal
 * @Date 2021/1/25 19:29
 */
public class ProductDao extends BaseDao<Product> {

    //通过is_hot展示热门商品
    public List<Product> hotProductList() throws SQLException {
        String sql = "select * from product where is_hot = ? limit ?,?";
        return this.getBeanList(sql,Product.class,1,0,9);
    }

    //通过pdate(上架时间)展示热门商品
    public List<Product> newProductList() throws SQLException {
        String sql = "select * from product order by pdate desc limit ?,?";
        return this.getBeanList(sql,Product.class,0,9);
    }

    //通过cid和pa=name查询符合条件商品总记录数
    public int selectCounts(String cid, String pname) throws SQLException {
        Long counts = 0L;

        if(cid == null || cid.equals("")){
            //只有表单内容搜索
            String sql = "select count(*) from product where pname like concat('%',?,'%')";
            counts = (Long) this.getSingleValue(sql,Product.class,pname);
        }else {
            //商品类别 + 表单内容搜索
            String sql = "select count(*) from product where cid = ? and pname like concat('%',?,'%')";
            counts = (Long) this.getSingleValue(sql,Product.class,cid,pname);
        }
        return counts.intValue();
    }

    //通过cid和pa=name查询商品列表并做分页
    public List<Product> productListBycidpname(String cid, String pname, int begin) throws SQLException {
        List<Product> productList = null;

        if(cid == null || cid.equals("")){
            //只有表单内容搜索
            String sql = "select * from product where pname like concat('%',?,'%') limit ?,12";
            productList = this.getBeanList(sql, Product.class, pname,begin);
        }else {
            //商品类别 + 表单内容搜索
            String sql = "select * from product where cid = ? and pname like concat('%',?,'%') limit ?,12";
            productList = this.getBeanList(sql, Product.class,cid, pname,begin);
        }
        return productList;
    }

}
