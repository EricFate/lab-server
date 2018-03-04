package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.HibernateUtils;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;


public class CollegeLoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	private final String FIND_COLLEGESTUDENT_ACCOUNT = "FROM CollegeStudent c WHERE c.username = :name";
	/**
	 * Constructor of the object.
	 */
	public CollegeLoginServlet() {
		super();
	}
	private Configuration configuration = null;
	private SessionFactory sessionFactory = null;
	private Session session = null;

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

		doPost(request, response);

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
		response.setContentType("string");
		PrintWriter out = response.getWriter();
		String username;
		String password;
		if(request==null)
			return;
		System.out.println(request.getParameter("username")+request.getParameter("password"));
		username=request.getParameter("username");
		password=request.getParameter("password");
		Session session=HibernateUtils.openSession();
		CollegeStudent collegeStudent=null;
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_COLLEGESTUDENT_ACCOUNT);
			query.setParameter("name", username);
			collegeStudent = (CollegeStudent)query.uniqueResult();
			Hibernate.initialize(collegeStudent);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
			
		}
		if(collegeStudent==null){
			out.flush();
			out.close();
		}
		if(collegeStudent.getPassword().equals(password)){

			collegeStudent.setAnswers(null);
			collegeStudent.setVideos(null);
			collegeStudent.setQuestions(null);
			collegeStudent.setAdminClass(null);
			collegeStudent.setStudents(null);
			out.print((new Gson()).toJson(collegeStudent));
		}else{
			out.print("fail");
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
		System.out.println("login start");

	}

}
