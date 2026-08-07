package zx.controller;

import zx.bean.Users;
import zx.dao.UserDao;
import zx.service.UserService;
import zx.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/RetrieveController")
public class RetrieveController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 1获取客户端数据
      String Ret_username = request.getParameter("Ret_username");
      String Ret_userpwd = request.getParameter("Ret_userpwd");
      String Ret_qruserpwd = request.getParameter("Ret_qruserpwd");
      String currentPwdMD5 = request.getParameter("oldPwdMD5");

      System.out.println("Ret_username = " + Ret_username);
      System.out.println("Ret_userpwd = " + Ret_userpwd);
      System.out.println("Ret_qruserpwd = " + Ret_qruserpwd);

      // 检查两次输入的密码是否相同
      if(!Ret_userpwd.equals(Ret_qruserpwd)){
         request.setAttribute("passwordCheck", "两次输入密码不一致！");
         request.getRequestDispatcher("retrieve.jsp").forward(request, response);
         return;
      }

      // 2封装数据,调用业务逻辑层来处理数据
      Users user = new Users();
      user.setName(Ret_username);
      String pwdmd5 = MD5Util.getMD5Str(Ret_userpwd);
      user.setPwd(pwdmd5);

      // 3根据业务逻辑层的返回结果做出不同的响应
      response.setContentType("text/html;charset=utf-8");

      if(UserService.Retrieve(user)){
         response.getWriter().write("修改成功！ 5s后跳转到登录页面...");
         response.setHeader("refresh", "5;login.jsp");
      } else {
         response.getWriter().write("该用户不存在，请注册！ 5s后跳转到注册页面...");
         response.setHeader("refresh", "5;register.jsp");
      }

   }
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }
}