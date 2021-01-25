package com.igeek.mall.control;

import com.igeek.mall.entity.User;
import com.igeek.mall.service.UserService;
import com.igeek.mall.utils.CommonUtils;
import com.igeek.mall.utils.MD5Utils;
import com.igeek.mall.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/userServlet")
public class UserServlet extends BasicServlet {

    //关联UserService
    private UserService service = new UserService();

    //注册
    public void register(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据name = value 进行获取键值对，例：name=username  value=文本和输入框中的内容
        Map<String, String[]> parameterMap = request.getParameterMap();

        //处理String -> Date 类型
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class aClass, Object o) {
                Date birthday = null;
                if(o instanceof String){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        birthday = simpleDateFormat.parse(request.getParameter("birthday"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return birthday;
            }
        }, Date.class);

        User user = new User();

        //通过BeanUtils工具类，给user对象对应的属性根据键值对赋值
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //设置uid
        user.setUid(CommonUtils.getUUID().replaceAll("-",""));
        //设置code
        String code = CommonUtils.getUUID().replaceAll("-", "");
        user.setCode(code);
        //设置密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));

        //注册
        boolean flag = service.register(user);
        if(flag){
            //注册成功
            System.out.println("注册成功");
            //发送邮件的主题内容
            String emailMess = "恭喜您注册成功，这是一封激活邮件，请点击<a href='http://localhost:8080/userServlet?method=active&code="+code+"'>"+code+"</a>激活";
            try {
                MailUtils.sendMail(user.getEmail(),"注册邮件激活",emailMess);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("registSuccess.jsp").forward(request,response);
        }else {
            //注册失败
            System.out.println("注册失败");
            request.getRequestDispatcher("registFail.jsp").forward(request,response);
        }

    }

    //用户激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        boolean flag = service.activeState(code);
        if(flag){
            //激活成功
            response.sendRedirect("login.jsp");
        }else {
            //激活失败
            response.sendRedirect("error.jsp");
        }
    }

    //校验(用户名校验)
    public void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean flag = service.validate(username);
        //封装成json数据格式响应至客户端   json串：{"flag":flag}
        String str = "{\"flag\":"+flag+"}";
        response.getWriter().write(str);
    }

    //登录

    //登出


}
