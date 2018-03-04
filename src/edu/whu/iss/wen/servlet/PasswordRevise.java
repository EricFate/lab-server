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

public class PasswordRevise extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PasswordRevise() {
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

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uid=request.getParameter("uid");
		String pass=request.getParameter("password");
		TeacherService ts=new TeacherService();
		UniversalResult universalResult=ts.passwordReviseInService(uid, pass);
		Gson gson=new Gson();
		String json=gson.toJson(universalResult);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	
	}
}
