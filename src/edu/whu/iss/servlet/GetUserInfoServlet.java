package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Info;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;

public class GetUserInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetUserInfoServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String muid = request.getParameter("muid");
		String quid = request.getParameter("quid");
		Info userInfo = UserService.getUserInfo(quid,muid);
		
		out.write(GsonUtil.toJson(userInfo));

		
		out.flush();
		out.close();
	}

}
