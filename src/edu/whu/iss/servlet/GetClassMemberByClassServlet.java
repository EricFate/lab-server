package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Info;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;

public class GetClassMemberByClassServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetClassMemberByClassServlet() {
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
		int clid = Integer.parseInt(request.getParameter("clid"));
		int type = Integer.parseInt(request.getParameter("type"));
		List<Info> member = UserService.getClassMember(clid,type);
		String json = GsonUtil.toJson(member);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}

}
