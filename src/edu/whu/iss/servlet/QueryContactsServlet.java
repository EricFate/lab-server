package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.ResultItem;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;

public class QueryContactsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QueryContactsServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String query = request.getParameter("query");
		String uid = request.getParameter("uid");
		query = new String(query.getBytes("ISO-8859-1"),"UTF-8");
		List<ResultItem> list = UserService.queryContacts(uid,query);
		out.write(GsonUtil.toJson(list));
		out.flush();
		out.close();
	}

}
