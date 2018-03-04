package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Result;
import edu.whu.iss.service.IssueService;
import edu.whu.iss.utils.GsonUtil;

public class DeleteIssueServlet extends HttpServlet {
	IssueService service = IssueService.getInstance();
	/**
	 * Constructor of the object.
	 */
	public DeleteIssueServlet() {
		super();
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
		int iid = Integer.parseInt(request.getParameter("iid")) ;
		service.deleteIssue(iid);
		out.write(GsonUtil.toJson(new Result(0, null)));
		out.flush();
		out.close();
	}

}
