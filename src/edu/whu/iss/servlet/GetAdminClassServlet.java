package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.service.SubjectService;
import edu.whu.iss.utils.GsonUtil;
import edu.whu.iss.wen.bean.AdminClass;

public class GetAdminClassServlet extends HttpServlet {
	private SubjectService service = SubjectService.getInstance();
	/**
		 * Constructor of the object.
		 */
	public GetAdminClassServlet() {
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
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int acid = Integer.parseInt(request.getParameter("acid"));
		AdminClass adminClass = service.getAdminClass(acid);
		
		if (adminClass==null) {
			adminClass=new AdminClass();
		}
		
		String json = GsonUtil.toJson(adminClass);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
