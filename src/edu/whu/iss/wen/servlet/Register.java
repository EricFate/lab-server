package edu.whu.iss.wen.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import com.google.gson.Gson;

import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.service.TeacherService;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
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
		
		Map<String, String[]> map = request.getParameterMap();
		Teacher teacher=new Teacher();
		try {
			BeanUtils.populate(teacher, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		TeacherService teacherService=new TeacherService();
		ForIdResult registerResult=teacherService.registerTeacherInService(teacher);
		Gson gson=new Gson();
		String json=gson.toJson(registerResult);
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
	
//	public String acceptJSON(HttpServletRequest request) {
//		String acceptjson = "";
//		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					(ServletInputStream) request.getInputStream(), "utf-8"));
//			StringBuffer sb = new StringBuffer("");
//			String temp;
//			while ((temp = br.readLine()) != null) {
//				sb.append(temp);
//			}
//			br.close();
//			acceptjson = sb.toString();
//			System.out.print(acceptjson);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return acceptjson;
//	}

}

