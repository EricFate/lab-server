package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.Answer;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

public class GetAnswerServlet extends HttpServlet {
	private final String FIND_QUESTION = "from Question q where q.id=:questionId";

	/**
	 * Constructor of the object.
	 */
	public GetAnswerServlet() {
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
		String id = null;
		id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		if(id!=null){
			Session session = HibernateUtils.openSession();
			try{

				session.beginTransaction();
				Query query = session.createQuery(FIND_QUESTION);
				query.setParameter("questionId", id);
				Question question = (Question)query.uniqueResult();
				Set<Answer> answers = question.getAnswers();
				out.print(GsonUtil.toJson(answers));
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();
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
	}

}