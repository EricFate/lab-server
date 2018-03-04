package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.bean.Result;
import edu.whu.iss.service.IssueService;
import edu.whu.iss.utils.GsonUtil;

public class WriteAnswerServlet extends HttpServlet {
	IssueService issueService = new IssueService();
	/**
	 * Constructor of the object.
	 */
	public WriteAnswerServlet() {
		super();
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String uid = request.getParameter("uid");
		int iid = Integer.parseInt(request.getParameter("iid"));
		String content = request.getParameter("content");
		boolean anonymous = Boolean.parseBoolean(request.getParameter("anonymous"));
		Answer answer = new Answer();
		answer.setAnonymous(anonymous);
		answer.setContent(content);
		answer.setTime(new Date());
		issueService.saveAnswer(answer, uid, iid);
		out.write(GsonUtil.toJson(new Result(0, "")) );
		out.flush();
		out.close();
	}

}
