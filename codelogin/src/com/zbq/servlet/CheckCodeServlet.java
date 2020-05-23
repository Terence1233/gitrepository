package com.zbq.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //创建画笔对象
        Graphics cs = image.getGraphics();
        //填充背景
        cs.setColor(Color.white);
        cs.fillRect(0, 0, width, height);
        //画边框
        cs.setColor(Color.BLUE);
        cs.drawRect(0, 0, width-1, height-1);
        //创建字符串
        String str = "ABCDEFGHIJKLMNO123456789fshdfsjdfjjsk";
        //生成随机角标
        Random dom = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<4 ;i++){
            int index = dom.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            //写字符
            cs.drawString(ch+"", width/5*i+6, height/2);
            sb.append(ch);
        }
        String checkCode_session = sb.toString();
        //将验证码存入session中
        request.getSession().setAttribute("checkCode_session", checkCode_session);
        //画干扰线
        cs.setColor(Color.RED);
        for(int i = 0; i<10 ;i++){
            int x1 = dom.nextInt(width);
            int x2 = dom.nextInt(width);

            int y1 = dom.nextInt(height);
            int y2 = dom.nextInt(height);
            cs.drawLine(x1, x2, y1, y2);
        }
        //将图片输出到页面展示
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
