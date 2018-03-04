package edu.whu.iss.sd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.RosterGroup;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.UserGroup;
import edu.whu.iss.dao.UserDAO;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.utils.RongCloudUtils;
import io.rong.RongCloud;
import io.rong.models.TokenReslut;

public class AddPairStudentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddPairStudentServlet() {
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

		PrintWriter out = response.getWriter();
		Session session = HibernateUtils.getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Student s where s.id = :studentId");
			String StudentId = request.getParameter("studentId");

			query.setParameter("studentId", Integer.parseInt(StudentId));
			Student student = (Student)query.uniqueResult();

			Query query2 = session.createQuery("from CollegeStudent c where c.id=:collegeStudentId");
			String CollegeStudentId = request.getParameter("collegeStudentId");
			query2.setParameter("collegeStudentId", Integer.parseInt(CollegeStudentId));
			CollegeStudent collegeStudent = (CollegeStudent)query2.uniqueResult();

			//System.out.println("add pair"+student);
			String uid = "c"+collegeStudent.getId();
			RosterGroup rosterGroup = new RosterGroup("δ����",uid);
			session.save(rosterGroup);
			String region = student.getRegion().replace("_","")+"Ⱥ";
			ChatGroup group = UserDAO.getGroupByName(region);
			UserGroup userGroup = new UserGroup();
			userGroup.setUid(uid);
			session.save(userGroup);
			group.getUserGroups().add(userGroup);
			int gid = (Integer)session.save(group);
			RongCloud instance = RongCloudUtils.getInstance();
			TokenReslut token = instance.user.getToken(uid,collegeStudent.getNickname(),"");
			if(token.getCode()!=200){
				System.out.println(token.getErrorMessage());
			}else{
				System.out.println(token.getToken());
			}
			instance.group.join(new String[]{uid},"g"+gid,region);

			student.setCollegeStudent(collegeStudent);
		}catch(Exception e){
			e.printStackTrace();
		}finally{

			session.getTransaction().commit();
//			session.close();
		}

		//System.out.println(collegeStudent);
		out.print("success");
		out.flush();
		out.close();
	}

}
