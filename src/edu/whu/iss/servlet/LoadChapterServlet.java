package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.service.SubjectService;
import edu.whu.iss.utils.GsonUtil;
import edu.whu.iss.wen.bean.Chapter;

public class LoadChapterServlet extends HttpServlet {
	SubjectService service = SubjectService.getInstance();
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
		int cid = Integer.parseInt(request.getParameter("cid")) ;
		List<Chapter> chapters = service.loadChapters(cid);
		String json = GsonUtil.toJson(chapters);
		out.write(json);
		out.flush();
		out.close();
	}

}
