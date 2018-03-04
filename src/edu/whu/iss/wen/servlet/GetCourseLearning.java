package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.CourseLearning;
import edu.whu.iss.wen.service.StudentService;
import edu.whu.iss.wen.utils.GsonUtil;

public class GetCourseLearning extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCourseLearning() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		Set<CourseLearning> set=new StudentService().getCourseLearningInService(id);
		Date date = new Date();
		ArrayList<String> days = new ArrayList<String>(); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i =0;i<7;i++){
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			days.add(simpleDateFormat.format(date));
		}
		System.out.println(days);
		ArrayList<CourseLearning> courses = new ArrayList<CourseLearning>();
		for(Iterator<CourseLearning> iterator=set.iterator();iterator.hasNext();){
			CourseLearning course = iterator.next();
			String time = simpleDateFormat.format(course.getTime());
			System.out.println(time);
			if(days.indexOf(time)!=-1){
				courses.add(course);
			}
		}
		System.out.println(courses.size());
		String json = GsonUtil.toJson(courses);
		PrintWriter out = response.getWriter();
		out.print(json);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
