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
//		subjects.add(new Subject("�������ĵ�һ��", "10000��ѧϰ", "28��ʱ", "image/640.jpg","���"));
//		subjects.add(new Subject("�������ĵڶ���", "10000��ѧϰ", "28��ʱ", "image/650.jpg","���"));
//		subjects.add(new Subject("�������ĵ�����", "10000��ѧϰ", "28��ʱ", "image/660.jpg","���"));
//		subjects.add(new Subject("������ѧ��һ��", "10000��ѧϰ", "10��ʱ", "image/540.jpg","������"));
//		subjects.add(new Subject("������ѧ�ڶ���", "10000��ѧϰ", "10��ʱ", "image/550.jpg","������"));
//		subjects.add(new Subject("������ѧ������", "10000��ѧϰ", "10��ʱ", "image/560.jpg","������"));
		List<TotalCourseLearning> subjects = service.getMySubjects(id, start);
		out.write(GsonUtil.toJson(subjects));
		out.flush();
		out.close();
	}

}
