package edu.whu.iss.sd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.bean.Student;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;

public class LoadUnpairStudentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadUnpairStudentServlet() {
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
		Session session = HibernateUtils.openSession();
		int start =-1;
		List<Student> students=null;
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Student s");
			students = query.list();
			start= Integer.parseInt(request.getParameter("start"));

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();

		}
		if(students!=null&&start!=-1&&start<students.size()){
			ArrayList<Student> students2 = new ArrayList();
			for(int i =start;(i<students.size())&&(i<=start+10);i++){
				Student student = students.get(i);
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

				students2.add(student);
			}
			out.print(GsonUtil.toJson(students2));
		}else{
			out.print("fail");
		}
		out.flush();
		out.close();
	}

}
