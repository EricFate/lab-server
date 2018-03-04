package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.service.TeacherService;

public class DeleteExerciseServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public DeleteExerciseServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int exerciseId = Integer.valueOf(request.getParameter("eid"));
		int type = Integer.valueOf(request.getParameter("type"));
		TeacherService teacherService = new TeacherService();
		UniversalResult universalResult = teacherService.deleteExercise(exerciseId, type);
		Gson gson = new Gson();
		String json = gson.toJson(universalResult);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		*/
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
