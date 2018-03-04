package edu.whu.iss.sd.servlet;

import edu.whu.iss.bean.RosterGroup;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.utils.RongCloudUtils;

import io.rong.models.TokenReslut;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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

		if(request==null)
			return;
		PrintWriter out = response.getWriter();
		CollegeStudent collegeStudent = new CollegeStudent();
		String username = request.getParameter("username");
		collegeStudent.setUsername(username);
		collegeStudent.setPassword(request.getParameter("password"));
		collegeStudent.setNickname(username);
		Session session = HibernateUtils.openSession();

		try {
			session.beginTransaction();
			int id = (Integer) session.save(collegeStudent);
			String uid = "c" + id;
			RosterGroup rosterGroup = new RosterGroup("δ����", uid);
			session.save(rosterGroup);
			TokenReslut token = RongCloudUtils.getInstance().user.getToken("c"+id, username, "");
			collegeStudent.setToken(token.getToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
			
		}
		out.print("ture");
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
		System.out.println("register collegestudent");
	}

}
