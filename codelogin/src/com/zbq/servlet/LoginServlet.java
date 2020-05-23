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
        //���ñ���
        response.setCharacterEncoding("utf-8");
        //��ȡ����
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //�ж���֤���Ƿ���ȷ
        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");
        //ɾ��session�д洢����֤��
        session.removeAttribute(checkCode_session);
        //�ж���֤���Ƿ���ȷ,���Դ�Сд
//        System.out.println(checkCode_session+"----"+checkCode);
        if(checkCode_session.equalsIgnoreCase(checkCode)){
            //���Դ�Сд�Ƚ�
            //�ж��û����������Ƿ���ȷ
            //��Ҫ����UserDao���ݿ��ѯ
            if("admin".equals(username) && "123".equals(password)){
                //��½�ɹ�
                //�洢��Ϣ
                session.setAttribute("user", username);
                //�ض���success.jsp
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else{
                request.setAttribute("login_error", "�û������������");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }else{
            //��֤�벻��ȷ
            //�洢��ʾ��Ϣ��request
            request.setAttribute("cc_error", "��֤�����");
            //ת������¼ҳ��
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
