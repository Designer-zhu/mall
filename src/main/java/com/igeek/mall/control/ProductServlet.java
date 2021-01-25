package com.igeek.mall.control;

import com.igeek.mall.entity.Product;
import com.igeek.mall.service.CategoryService;
import com.igeek.mall.service.ProductService;
import com.igeek.mall.vo.PageVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/productServlet")
public class ProductServlet extends BasicServlet{

    private ProductService service = new ProductService();

    //展示首页
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> hotProducts = service.showHot();
        request.setAttribute("hotProducts",hotProducts);

        List<Product> newProducts = service.showNew();
        request.setAttribute("newProducts",newProducts);

        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    //条件搜索 + 分页
    public void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取查询条件 cid
        String cid = request.getParameter("cid");
        if(cid != null && !"".equals(cid)){
            CategoryService cs = new CategoryService();
            String cname = cs.selectCategoryNameBycid(cid);
            request.setAttribute("cname",cname);
        }

        //获取查询条件 pname
        String pname = request.getParameter("pname");
        if(pname==null){
            pname = "";
        }

        //获取当前页
        String page = request.getParameter("pageNow");
        int pageNow = 1;
        if(page != null){
            pageNow = Integer.parseInt(page);
        }
        PageVo<Product> pageVo = service.viewProductsByCidPname(cid, pname, pageNow);
        request.setAttribute("pageVo",pageVo);

        request.getRequestDispatcher("product_list.jsp").forward(request,response);
    }

}
