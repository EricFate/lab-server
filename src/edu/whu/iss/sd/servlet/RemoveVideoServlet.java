package edu.whu.iss.sd.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.sd.bean.HibernateUtils;

public class RemoveVideoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RemoveVideoServlet() {
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


		PrintWriter out = response.getWriter();
		String id = request.getParameter("videoId");
		if(id!=null){
			String deleteVideo = "delete Video as video where video.id = :videoId";
			Session session = HibernateUtils.openSession();
			try{
				session.beginTransaction();
				String pathString =request.getServletContext().getRealPath("")+File.separator+request.getParameter("videoUrl");
				Query query = session.createQuery(deleteVideo);
				query.setParameter("videoId", Integer.parseInt(id));
				query.executeUpdate();

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();
			}
//			if(deleteFile(pathString)==false){
//				out.print("false");
//				out.flush();
//				out.close();
//				return;
//			}else{

//			}
		}else{
			System.out.println("delete video fail");
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


	public boolean deleteFile(String sPath) {
	    File file = new File(sPath);
	    // ·��Ϊ�ļ��Ҳ�Ϊ��������ɾ��
	    if (file.isFile() && file.exists()) {
	        file.delete();
	        return true;
	    }
	    return false;
	}
}
