package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Answer;
import edu.whu.iss.wen.service.StudentService;
import edu.whu.iss.wen.utils.GsonUtil;

public class GetSingleAnswer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetSingleAnswer() {
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
		int id=Integer.parseInt(request.getParameter("answerId"));
		StudentService service=new StudentService();
		Answer a=service.getSingleAnswerInService(id);
		PrintWriter out=response.getWriter();
		out.print(GsonUtil.toJson(a));
		out.flush();
		out.close();

	}

}
