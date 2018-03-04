package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.TotalCourseLearning;
import edu.whu.iss.service.SubjectService;
import edu.whu.iss.utils.GsonUtil;

public class LoadMyLessonServlet extends HttpServlet {
	SubjectService service = new SubjectService();
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int start = Integer.parseInt(request.getParameter("start"));
//		List<Subject> subjects = service.getMySubjects(uid,start);
//		ArrayList<Subject> subjects = new ArrayList<Subject>();
//		subjects.add(new Subject("高中语文第一册", "10000人学习", "28课时", "image/640.jpg","沈达"));
//		subjects.add(new Subject("高中语文第二册", "10000人学习", "28课时", "image/650.jpg","沈达"));
//		subjects.add(new Subject("高中语文第三册", "10000人学习", "28课时", "image/660.jpg","沈达"));
//		subjects.add(new Subject("高中数学第一册", "10000人学习", "10课时", "image/540.jpg","温亚洲"));
//		subjects.add(new Subject("高中数学第二册", "10000人学习", "10课时", "image/550.jpg","温亚洲"));
//		subjects.add(new Subject("高中数学第三册", "10000人学习", "10课时", "image/560.jpg","温亚洲"));
		List<TotalCourseLearning> subjects = service.getMySubjects(id, start);
		out.write(GsonUtil.toJson(subjects));
		out.flush();
		out.close();
	}

}
