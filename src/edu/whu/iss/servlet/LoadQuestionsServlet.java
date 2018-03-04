package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Question;
import edu.whu.iss.service.ExerciseService;
import edu.whu.iss.utils.GsonUtil;
import edu.whu.iss.wen.bean.Exercise;

public class LoadQuestionsServlet extends HttpServlet {
	private ExerciseService service = ExerciseService.getInstance();
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

//		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int lid = Integer.parseInt(request.getParameter("lid"));
		Set<Exercise> questions = service.getLessonExercises(lid);
		out.write(GsonUtil.toJson(questions));
		out.flush();
		out.close();
	}

}
