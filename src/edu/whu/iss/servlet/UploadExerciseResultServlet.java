package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.whu.iss.bean.Result;
import edu.whu.iss.service.ExerciseService;
import edu.whu.iss.utils.GsonUtil;

public class UploadExerciseResultServlet extends HttpServlet {
	private ExerciseService service = ExerciseService.getInstance();
	/**
		 * Constructor of the object.
		 */
	public UploadExerciseResultServlet() {
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
		String content = request.getParameter("content");
		int id = Integer.parseInt(request.getParameter("id"));
		Map<Integer, Integer> object = GsonUtil.fromJson(content, new TypeToken<Map<Integer, Integer>>(){}.getType());
		String ecid = request.getParameter("ecid");
		service.saveResult(id, object,ecid);
		out.write(GsonUtil.toJson(new Result(0,null)));
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
