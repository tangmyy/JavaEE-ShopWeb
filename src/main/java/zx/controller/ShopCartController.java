package zx.controller;

import zx.bean.MyShopCartDate;
import zx.service.ShopCartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/ShopCartController")
public class ShopCartController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 获取客户端传递来的参数
      String commodityId = request.getParameter("commodityId");
      String serviceType = request.getParameter("serviceType");
      String changeNum = request.getParameter("changeNum");
      MyShopCartDate myShopCartDate = (MyShopCartDate) request.getSession().getAttribute("myShopCartDate");    // 强制转换


      if(myShopCartDate == null) {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json; charset=UTF-8");
         response.getWriter().write("{\"loggedIn\": false}");
//         response.sendRedirect("login.jsp");
         return;
      }

      boolean success = false;

      if (commodityId != null) {
         if ("updateByNum".equals(serviceType) && changeNum != null) {
            try {
               int changeNumber = Integer.parseInt(changeNum);
               ShopCartService.dispathServiceType(serviceType, commodityId, myShopCartDate, changeNumber);
               success = true;
            } catch (NumberFormatException e) {
               e.printStackTrace();
            }
         } else {
            ShopCartService.dispathServiceType(serviceType, commodityId, myShopCartDate);
            success = true;
         }
      }

      boolean cartEmpty = myShopCartDate.getCartList().isEmpty();
      System.out.println(myShopCartDate);      //  !!!!!!***控制台输出语句***!!!!!!


//      response.sendRedirect("myshopcart.jsp");
      response.getWriter().write("{\"loggedIn\": true, \"success\": " + success + ", \"total\": \"" + myShopCartDate.getTotal() + "\", \"cartEmpty\": " + cartEmpty + "}");
   }


   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
