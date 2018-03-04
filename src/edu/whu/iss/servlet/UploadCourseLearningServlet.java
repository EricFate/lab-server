package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Result;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.service.SubjectService;

public class UploadCourseLearningServlet extends HttpServlet {
	SubjectService service = SubjectService.getInstance();
	/**
	 * Constructor of the object.
	 */
	public UploadCourseLearningServlet() {
		super();
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
		int sid = Integer.parseInt(request.getParameter("sid")) ;
		int cid = Integer.parseInt(request.getParameter("cid")) ;
		long duration = Long.parseLong(request.getParameter("duration")) ;
		service.courseLearning(sid,cid,duration);
		out.write(GsonUtil.toJson(new Result(0, null)));
		out.flush();
		out.close();
	}

}
