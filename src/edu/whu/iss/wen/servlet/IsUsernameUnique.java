package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.service.TeacherService;

public class IsUsernameUnique extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public IsUsernameUnique() {
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

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		TeacherService teacher=new TeacherService();
		UniversalResult result=teacher.isUsernameUniqueInService(username);
		PrintWriter out=response.getWriter();
		out.print(GsonUtil.toJson(result));
		out.flush();
		out.close();
		
	}

}
