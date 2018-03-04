package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Video;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

public class AddVideoPlayNumber extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddVideoPlayNumber() {
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
		String FINF_VIDEO = "from Video v where v.videoId= :videoId";
		PrintWriter out = response.getWriter();
		int id = 0;
		id = Integer.parseInt(request.getParameter("videoId"));
		if(id!=0){
			Session session = HibernateUtils.openSession();
			try{
				session.beginTransaction();
				Query query = session.createQuery(FINF_VIDEO);
				query.setParameter("videoId", id);
				Video video = (Video)query.uniqueResult();
				video.setPlaynumber(video.getPlaynumber()+1);

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();
				out.print("succeed");

			}
		}else {
			out.print("fail");
			System.out.println("not found video id");
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
		System.out.println("add video number");
	}

}
