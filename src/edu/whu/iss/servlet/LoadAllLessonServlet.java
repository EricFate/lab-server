package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Major;
import edu.whu.iss.service.SubjectService;
import edu.whu.iss.utils.GsonUtil;

public final class LoadAllLessonServlet extends HttpServlet {
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
		PrintWriter writer = response.getWriter();
		
//		Gson gson = new Gson();
//		ArrayList<Category> catagories = new ArrayList<Category>();
//		ArrayList<Subject> chinese = new ArrayList<Subject>();
//		chinese.add(new Subject("高中语文第一册", "100人学习", "28课时", "image/course/640.jpg"));
//		chinese.add(new Subject("高中语文第二册", "120人学习", "28课时", "image/650.jpg"));
//		chinese.add(new Subject("高中语文第三册", "167人学习", "28课时", "image/660.jpg"));
//		ArrayList<Subject> maths = new ArrayList<Subject>();
//		maths.add(new Subject("高中数学第一册", "100人学习", "10课时", "image/540.jpg"));
//		maths.add(new Subject("高中数学第二册", "100人学习", "10课时", "image/550.jpg"));
//		maths.add(new Subject("高中数学第三册", "100人学习", "10课时", "image/560.jpg"));
//		catagories.add(new Category("高中语文",chinese));
//		catagories.add(new Category("高中数学", maths));
//		String json = gson.toJson(catagories);
		List<Major> list = service.getAllSubjects();
		String json = GsonUtil.toJson(list);
		System.out.println(json);
		writer.write(json);
		writer.flush();
	}

}
