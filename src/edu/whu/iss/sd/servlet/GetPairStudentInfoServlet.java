package edu.whu.iss.sd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.bean.Student;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;

public class GetPairStudentInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPairStudentInfoServlet() {
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String cid = request.getParameter("collegeStudentId");
		if(cid!=null){
			Session session = HibernateUtils.openSession();
			CollegeStudent collegeStudent=null;
			try{
				session.beginTransaction();
				Query query = session.createQuery("from CollegeStudent c where c.id=:cid");
				query.setParameter("cid", Integer.parseInt(cid));
				collegeStudent = (CollegeStudent)query.uniqueResult();
				Hibernate.initialize(collegeStudent.getStudents());

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();
			}
			if(collegeStudent!=null){
				Set<Student> students = collegeStudent.getStudents();
				for(Iterator<Student> iterator=students.iterator();iterator.hasNext();){
					Student student = iterator.next();
					student.setAdminClass(null);
					student.setCollegeStudent(null);
					student.setCourses(null);
					student.setIssues(null);
					student.setRanks(null);
					student.setLearnings(null);
					student.setTotalLearnings(null);
					student.setExCateDetails(null);
					student.setExDetails(null);
					student.setTotalMessages(null);
					student.setParent(null);
				}
				out.print(GsonUtil.toJson(students));
			}else{
				out.print("fail, no collegestudent");
			}
		}else{
			out.print("fail,no cid");
		}

		out.flush();
		out.close();
	}

}
