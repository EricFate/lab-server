package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.whu.iss.wen.bean.Exercise;
import edu.whu.iss.wen.service.TeacherService;

public class GetTest extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public GetTest() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String tid = request.getParameter("tid");
		List<Exercise> s=new TeacherService().getTestInSetvice(tid);
		Gson gson = new Gson();
		String json = gson.toJson(s);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
