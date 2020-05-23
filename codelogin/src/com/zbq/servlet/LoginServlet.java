package com.zbq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setCharacterEncoding("utf-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //判断验证码是否正确
        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");
        //删除session中存储的验证码
        session.removeAttribute(checkCode_session);
        //判断验证码是否正确,忽略大小写
//        System.out.println(checkCode_session+"----"+checkCode);
        if(checkCode_session.equalsIgnoreCase(checkCode)){
            //忽略大小写比较
            //判断用户名和密码是否正确
            //需要调用UserDao数据库查询
            if("admin".equals(username) && "123".equals(password)){
                //登陆成功
                //存储信息
                session.setAttribute("user", username);
                //重定向到success.jsp
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else{
                request.setAttribute("login_error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }else{
            //验证码不正确
            //存储提示信息到request
            request.setAttribute("cc_error", "验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
