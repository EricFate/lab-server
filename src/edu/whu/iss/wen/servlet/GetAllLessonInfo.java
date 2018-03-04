package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.service.LessonService;
import edu.whu.iss.wen.utils.HibernateUtil;

public class GetAllLessonInfo extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public GetAllLessonInfo() {
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
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Course course = session.get(Course.class, courseId);
		transaction.commit();
		
		List<Integer> li=new ArrayList<Integer>();
		Set<Chapter> lc=course.getChapters();
		for(Chapter c:lc){
			li.add(c.getId());
		}
		List<Set<Lesson>> lessons=new LessonService().getAllLessonInfoInService(li);
		Gson gson = new Gson();
		String json = gson.toJson(lessons);
		System.out.println(lessons.size()+"dawdwadwa");
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
