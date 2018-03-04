package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Result;
import edu.whu.iss.service.SubjectService;
import edu.whu.iss.utils.GsonUtil;

public class SubmitRankServlet extends HttpServlet {
	SubjectService service = SubjectService.getInstance();
	/**
	 * Constructor of the object.
	 */
	public SubmitRankServlet() {
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

		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int cid = Integer.parseInt(request.getParameter("cid")) ;
		int id = Integer.parseInt(request.getParameter("id")) ;
		String content = request.getParameter("content");
		float rank = Float.parseFloat(request.getParameter("rank")) ;
		service.submitRank(id,content,rank,cid);
		out.write(GsonUtil.toJson(new Result(0, null)));
		out.flush();
		out.close();
	}

}
