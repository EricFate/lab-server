package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.HibernateUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

public class updateCollegeStudentServlet extends HttpServlet {
	private final String FIND_COLLEGESTUDENT="from CollegeStudent s where s.id= :userid";

	/**
	 * Constructor of the object.
	 */
	public updateCollegeStudentServlet() {
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
		Session session = HibernateUtils.openSession();
		try{

			session.beginTransaction();
			Query query = session.createQuery(FIND_COLLEGESTUDENT);
			CollegeStudent collegeStudent = null;
			int id = Integer.parseInt(request.getParameter("id"));
			if(id!=0){
				System.out.println(id);
				query.setParameter("userid",id);
				collegeStudent=(CollegeStudent)query.uniqueResult();
			}
			if(request.getParameter("nickname")!=null){
				collegeStudent.setNickname(request.getParameter("nickname"));
			}
			if(request.getParameter("password")!=null){
				collegeStudent.setPassword(request.getParameter("password"));
			}
			if(request.getParameter("region")!=null){
				collegeStudent.setRegion(request.getParameter("region"));

			}
			if(request.getParameter("school")!=null){
				collegeStudent.setSchool(request.getParameter("school"));
			}
			if(request.getParameter("signature")!=null){
				collegeStudent.setSignature(request.getParameter("signature"));

			}
			if(request.getParameter("imageURL")!=null){
				collegeStudent.setImageURL(request.getParameter("imageURL"));
			}
			if(request.getParameter("gender")!=null){
				System.out.println("gender: "+request.getParameter("gender"));
				collegeStudent.setGender(request.getParameter("gender"));
			}
			if(request.getParameter("major")!=null){
				collegeStudent.setMajor(request.getParameter("major"));
			}
			if(request.getParameter("introduction")!=null){
				collegeStudent.setIntroduction(request.getParameter("introduction"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
			
		}
		PrintWriter out = response.getWriter();
		out.print("success");
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
