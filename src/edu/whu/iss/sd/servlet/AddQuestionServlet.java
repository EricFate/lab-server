package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Question;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;

public class AddQuestionServlet extends HttpServlet {
	private final String FIND_COLLEGESTUDENT = "from CollegeStudent c where c.id= :userId";

	/**
	 * Constructor of the object.
	 */
	public AddQuestionServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Question question = (new Gson()).fromJson(request.getParameter("question"), Question.class);
		String id = request.getParameter("userId");
		System.out.println(id);
		if(id!=null){
			Session session= HibernateUtils.openSession();
			try{

				session.beginTransaction();
				Query query = session.createQuery(FIND_COLLEGESTUDENT);
				query.setParameter("userId", id);
				CollegeStudent collegeStudent = (CollegeStudent)query.uniqueResult();
				//			question.setDate(request.getParameter("itemTime"));
				//			question.setFoucusnumber(request.getParameter("peopleNum"));
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = simpleDateFormat.format(date);
				question.setDate(dateString);
				question.setCollegeStudent(collegeStudent);
				session.save(question);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();

			}
		}

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
