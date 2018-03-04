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

import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Video;

public class GetMyVideoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetMyVideoServlet() {
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
		String FIND_COLLEGESTUDENT = "from CollegeStudent c where c.id = :userid";
		PrintWriter out = response.getWriter();

		Session session = HibernateUtils.openSession();
		Set<Video> videos=null;
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_COLLEGESTUDENT);
			query.setParameter("userid", Integer.parseInt(request.getParameter("userid")));
			CollegeStudent collegeStudent = (CollegeStudent)query.uniqueResult();
			videos = collegeStudent.getVideos();
			Hibernate.initialize(videos);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		if(videos!=null){
			for(Iterator<Video> iterator=videos.iterator();iterator.hasNext();){
				iterator.next().setCollegeStudent(null);
			}
			out.print(GsonUtil.toJson(videos));

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
