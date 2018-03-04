package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

public class GetVideoServlet extends HttpServlet {
	private final String FIND_VIDEO = "from Video video";
	private final String FIND_COLLEGESTUDENT = "from CollegeStudent c where id = :userid";
	/**
	 * Constructor of the object.
	 */
	public GetVideoServlet() {
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("userId"));
		if(id!=0){
			System.out.println("get video id = "+id);
			Session session = HibernateUtils.openSession();
			List<Video> videos = null;
			try{
				session.beginTransaction();
				Query query = session.createQuery(FIND_VIDEO);
				videos = query.list();
				videos.getClass();

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();

			}
			if(videos!=null){
				for(Iterator<Video> iterator = videos.iterator();iterator.hasNext();){
					iterator.next().setCollegeStudent(null);
				}
				out.print(GsonUtil.toJson(videos));
				
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
		System.out.println("get video servlet has been done");
	}

}
