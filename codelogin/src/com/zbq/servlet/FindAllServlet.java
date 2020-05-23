package com.zbq.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
@WebServlet("/findUserServlet")
public class FindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("*---**-**--*-");
        if("tom".equals(username)){
            map.put("userExist", true);
            map.put("msg", "此用户名太受欢迎， 请更换一个");
        }else{
            map.put("userExist", false);
            map.put("msg", "此用户名可以使用");
        }
        //将msg转换为JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), map);
    }
    //获取用户名

}
