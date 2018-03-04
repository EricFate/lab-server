package edu.whu.iss.lu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.wen.bean.DayStudyInfo;
import edu.whu.iss.wen.service.StudentService;
import edu.whu.iss.wen.utils.GsonUtil;

public class ParentGetStudyInfo extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public ParentGetStudyInfo() {
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
		int id=Integer.parseInt(request.getParameter("id"));
		String uid="s"+id;
		Date now=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		int counter=0;
		int max=0;
		StudentService ss=new StudentService();
		List<DayStudyInfo> list=new ArrayList<DayStudyInfo>();
		while(counter<10){
			DayStudyInfo dsi=ss.getDayStudyInfoInService(now, id, uid);
			if(dsi.getLearning().size()!=0||dsi.getIssues().size()!=0||dsi.getAnswers().size()!=0||dsi.getMessages().size()!=0){
				counter++;
				list.add(dsi);
				
			}
			calendar.add(Calendar.DATE ,-1);
			now=calendar.getTime();
			max++;
			if(max>30)
				break;
		}
		PrintWriter out=response.getWriter();
		out.print(GsonUtil.toJson(list));
		System.out.println(GsonUtil.toJson(list));
		out.flush();
		out.close();
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
