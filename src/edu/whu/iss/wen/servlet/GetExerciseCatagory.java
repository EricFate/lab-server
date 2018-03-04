package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.whu.iss.wen.bean.ExerciseCatagory;
import edu.whu.iss.wen.service.TeacherService;

public class GetExerciseCatagory extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public GetExerciseCatagory() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String tid=request.getParameter("id");
		char t=tid.charAt(0);
		System.out.println(tid.substring(1)+"dawda´ïÍß´ïÍß´ïÍß");
		int id=Integer.parseInt(tid.substring(1));
		
		ExerciseCatagory set=new TeacherService().getExerciseCatagoryInService(t,id);
		Gson gson = new Gson();
		String json = gson.toJson(set);
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
	public void init() throws ServletException {
		// Put your code here
	}

}
