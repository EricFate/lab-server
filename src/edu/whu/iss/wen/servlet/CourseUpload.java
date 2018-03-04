package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import edu.whu.iss.bean.Major;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.result.CourseResult;
import edu.whu.iss.wen.service.CourseService;
import edu.whu.iss.wen.utils.HibernateUtil;


public class CourseUpload extends HttpServlet {
	private final static String GET_MAJOR_HQL = "from Major where title = :title";
	/**
	 * Constructor of the object.
	 */
	public CourseUpload() {
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
		response.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Teacher teacher=session.get(Teacher.class, Integer.parseInt(request.getParameter("teacherID")));
		String title = request.getParameter("major");
		Major m = (Major) session.createQuery(GET_MAJOR_HQL).setString("title", title).uniqueResult();
		if(m==null){
			m = new Major();
			m.setTitle(title);
			session.save(m);
		}
		transaction.commit();
		
		Course course=new Course();
		course.setName(request.getParameter("name"));
		course.setGrade(request.getParameter("grade"));
		course.setDescription(request.getParameter("description"));
		course.setSubject(request.getParameter("subject"));
		course.setSemester(request.getParameter("semester"));
		course.setTeacher(teacher);
		course.setMajor(m);		
		CourseService courseUploadService=new CourseService();
		CourseResult courseUploadResult=courseUploadService.courseUploadInService(course);
		Gson gson=new Gson();
		String json=gson.toJson(courseUploadResult);
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
