package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;

import edu.whu.iss.bean.MessageRecord;
import edu.whu.iss.bean.Result;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;

public class UploadMessageRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadMessageRecordServlet() {
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
		String content = request.getParameter("content");
		Type type = new TypeToken<List<MessageRecord>>(){}.getType();
		List<MessageRecord> records= GsonUtil.fromJson(content, type);
		UserService.messageRecord(records);
		out.write(GsonUtil.toJson(new Result(0, null)));
		out.flush();
		out.close();
	}

}
