package com.igeek.mall.control;

import com.google.gson.Gson;
import com.igeek.mall.entity.Category;
import com.igeek.mall.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/categoryServlet")
public class CategoryServlet extends BasicServlet {

    private CategoryService service = new CategoryService();

    //展示头部商品分类
    public void header(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoriesType = service.showCategoryType();

        //通过json数据的传递商品分类的集合
        Gson gson = new Gson();
        String str = gson.toJson(categoriesType);

        //将json数据响应至客户端
        response.getWriter().write(str);

    }
}
