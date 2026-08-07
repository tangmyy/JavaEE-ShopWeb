package zx.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zx.bean.MyShopCartDate;
import zx.bean.Users;
import zx.service.UserService;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logout = request.getParameter("logout");
		if (logout != null){
			HttpSession session = request.getSession();
				session.invalidate();	// 主动销毁会话
			response.sendRedirect("HomeController");
			return;
		}
		// 获取客户端数据
		String name = request.getParameter("username");
		String pwd = request.getParameter("userpwd");

		// 2封装数据,调用业务逻辑层来处理数据
	  	 Users user = new Users();
	  	 user.setName(name);
	  	 user.setPwd(pwd);


		// 【控制台提示】	System.out.println("LoginController Class doGet:"+user.toString());
		System.out.println("name = "+name);
		System.out.println("pwd = "+pwd);
		System.out.println("LoginController "+ user);


	  	 UserService userService = new UserService();
	  	 userService.Login(user); 
	  	 // 根据业务逻辑层的返回结果做出不同的响应
	  	 response.setContentType("text/html;charset=utf-8");
	  	 if(user.getId() == null){
			response.getWriter().write("您的用户名或者密码错误，请重新输入：");
			response.setHeader("refresh", "5;login.jsp");
	  	 }else if(user.getId() == -1) {
			response.getWriter().write("系统崩溃，请稍后再试！");
			response.setHeader("refresh", "5;login.jsp");
	  	 }else {
			 HttpSession session = request.getSession();
			 session.setAttribute("user", user);
			 MyShopCartDate myShopCartDate = new MyShopCartDate(user.getId());		// 购物车初始化
			 session.setAttribute("myShopCartDate", myShopCartDate);
			 response.sendRedirect("HomeController");
	  	 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
