package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Result;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;

public class InfoChangeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InfoChangeServlet() {
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


		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int id =Integer.parseInt(request.getParameter("id")) ;
		Map<String, String[]> map = request.getParameterMap();
		Map<String,String[]> maps = new HashMap<String, String[]>(map);
		maps.remove("id");
		UserService.changeInfo(id,maps);
		out.write(GsonUtil.toJson(new Result(0, "")));
		out.flush();
		out.close();
	}

}
