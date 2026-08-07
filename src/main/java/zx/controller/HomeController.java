
package zx.controller;

import zx.bean.HomeData;
import zx.service.CommodityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginDao
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取客户端输入
		String keyword = request.getParameter("keyword");
		int sort = (request.getParameter("sort")==null)?0:Integer.parseInt(request.getParameter("sort"));
		int curPage = (request.getParameter("curPage")==null)?1:Integer.parseInt(request.getParameter("curPage"));



		// 调用模型层处理业务
		HomeData homeData = new HomeData(keyword,sort,curPage);
		System.out.println(homeData);
		CommodityService.prepareForHomeData(homeData);

		// 将关键字信息设置到请求属性中，以便在页面中使用
		request.setAttribute("keyword", keyword);

		// 把处理后的数据传到相应的jsp上（响应客户端）
		request.setAttribute("homeData", homeData);											// key-value String-HomeData
		request.getRequestDispatcher("home.jsp").forward(request, response);		// request和response是什么？

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
