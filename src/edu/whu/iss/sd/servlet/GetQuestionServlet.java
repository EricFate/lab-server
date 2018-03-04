package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Question;

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

public class GetQuestionServlet extends HttpServlet {
	private final String FIND_COLLEGESTUDENT = "from CollegeStudent c where c.id=:userId";

	/**
	 * Constructor of the object.
	 */
	public GetQuestionServlet() {
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
		int id;
		System.out.println("get question no nest");
		id = Integer.parseInt(request.getParameter("userId"));
		if(id!=0){
			System.out.println("GetQusetionServlet: uerId== "+id);
			Session session = HibernateUtils.openSession();
			CollegeStudent collegeStudent=null;
			try{
				session.beginTransaction();
				Query query = session.createQuery(FIND_COLLEGESTUDENT);
				query.setParameter("userId", id);
				collegeStudent = (CollegeStudent)query.uniqueResult();
				Hibernate.initialize(collegeStudent.getQuestions());
			}catch(Exception e){
				e.printStackTrace();

			}finally{
				session.getTransaction().commit();
				session.close();

			}
			if(collegeStudent!=null){
				Set<Question> questions = collegeStudent.getQuestions();
				for(Iterator<Question> iterator=questions.iterator();iterator.hasNext();){
					Question question = iterator.next();
					System.out.println(question.toString());
					question.setAnswers(null);

					question.setCollegeStudent(null);
				}

				out.print(GsonUtil.toJson(questions));

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
