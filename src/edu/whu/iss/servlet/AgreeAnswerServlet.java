package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Result;
import edu.whu.iss.service.IssueService;
import edu.whu.iss.utils.GsonUtil;

public class AgreeAnswerServlet extends HttpServlet {
	private IssueService service = new IssueService();
	/**
	 * Constructor of the object.
	 */
	public AgreeAnswerServlet() {
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
		int aid = Integer.parseInt(request.getParameter("aid")) ;
		service.agreeAnswer(aid);
		out.write(GsonUtil.toJson(new Result(0, "")));
		out.flush();
		out.close();
	}

}
