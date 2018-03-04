package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.whu.iss.wen.result.LoginInResult;
import edu.whu.iss.wen.service.TeacherService;

public class LoginIn extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LoginIn() {
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

		LoginInResult loginInResult=new LoginInResult();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TeacherService teacherService=new TeacherService();
		System.out.println(request.getParameter("username")+request.getParameter("password")+"dawdawdaw");
		loginInResult=teacherService.loginInTeacherInService(request.getParameter("username"), request.getParameter("password"));
		System.out.println(loginInResult.getResultCode());
		Gson gson=new Gson();
		String json=gson.toJson(loginInResult);
		//responseµƒ…Ë÷√
		PrintWriter out=response.getWriter();
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
