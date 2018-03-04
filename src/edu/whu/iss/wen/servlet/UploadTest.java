package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Exercise;
import edu.whu.iss.wen.bean.ExerciseCatagory;
import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.service.TeacherService;
import edu.whu.iss.wen.utils.HibernateUtil;

public class UploadTest extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadTest() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ArrayList<Integer> al=request.getParameterMap().get("exercises");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String exercises = request.getParameter("exercises");
		int id = Integer.valueOf(request.getParameter("id"));
		int testType = Integer.valueOf(request.getParameter("testType"));
		String type = request.getParameter("type");
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String name = null;
		Lesson lesson=null;
		Chapter chapter=null;
		Course course=null;
		switch (testType) {
		case 0:
			lesson= session.get(Lesson.class, id);
			name = lesson.getLessonName();
			break;
		case 1:
			chapter= session.get(Chapter.class, id);
			name = chapter.getChapterName();
			break;
		}

		UniversalResult ur = new UniversalResult();
		if (type.equals("upload")) {
			ExerciseCatagory ec = new ExerciseCatagory();
			Set<Exercise> set = new HashSet<Exercise>();
			for (int i = 0; i < exercises.length(); i++) {
				int eid = Integer.parseInt(String.valueOf(exercises.charAt(i)));
				Exercise exercise = session.get(Exercise.class, eid);
				set.add(exercise);
			}
			ec.setName(name);
			
			switch (testType) {
			case 0:
				ec.setLesson(lesson);
				break;
			case 1:
				ec.setChapter(chapter);
				break;
			}
			ec.setExercises(set);
			transaction.commit();
			ur = new TeacherService().uploadTestInService(ec,id,testType);
		} else if (type.equals("add")) {
			ExerciseCatagory ec=null;
			switch (testType) {
			case 0:
				ec= lesson.getExerciseCatagory();
				break;
			case 1:
				ec = chapter.getExerciseCatagory();
				break;
			}

			Set<Exercise> set = ec.getExercises();
			for (int i = 0; i < exercises.length(); i++) {
				int eid = Integer.parseInt(String.valueOf(exercises.charAt(i)));
				Exercise exercise = session.get(Exercise.class, eid);
				set.add(exercise);
			}
			ur.setResultCode(1);
			transaction.commit();
		}

		Gson gson = new Gson();
		String json = gson.toJson(ur);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
