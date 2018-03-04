package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.service.IssueService;
import edu.whu.iss.utils.GsonUtil;

public class LoadMyAnswersServlet extends HttpServlet {
	IssueService service = IssueService.getInstance();
	/**
	 * Constructor of the object.
	 */
	public LoadMyAnswersServlet() {
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
		String uid = request.getParameter("uid");
		List<Answer> answers = service.loadAnswers(uid);
		String json = GsonUtil.toJson(answers);
		System.out.println(uid);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}

}
