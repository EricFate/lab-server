package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.PrivateExCate;
import edu.whu.iss.service.ExerciseService;
import edu.whu.iss.utils.GsonUtil;
import edu.whu.iss.wen.bean.ExerciseCatagory;

public class GetExerciseCategoryServlet extends HttpServlet {
	private ExerciseService service = ExerciseService.getInstance();
	/**
		 * Constructor of the object.
		 */
	public GetExerciseCategoryServlet() {
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
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int cid = Integer.parseInt(request.getParameter("cid")) ;
		int id = Integer.parseInt(request.getParameter("id"));
		List<PrivateExCate> catagories = service.getExerciseCatagories(id,cid);
		String json = GsonUtil.toJson(catagories);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
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
