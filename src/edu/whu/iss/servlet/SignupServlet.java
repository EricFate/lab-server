package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.whu.iss.bean.Result;
import edu.whu.iss.bean.Student;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;
import exceptions.AccountException;

public class SignupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String INSERT_SQL = "INSERT INTO t_students (username,password,nickname,region,realname,class,school,stu_number,phone,email) VALUES (?,?,?,?,?,?,?,?,?,?)";

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		Result result = null;
		PrintWriter writer = response.getWriter();
		Student user = new Student();
			try {
				BeanUtils.populate(user, parameterMap);
				UserService.registUser(user);
				result = new Result(0, "");
			} catch (AccountException e) {
				e.printStackTrace();
				
				result = new Result(1, e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				result = new Result(1, "²ÎÊý´íÎó");
			}
		writer.write(GsonUtil.toJson(result));
		writer.flush();
		writer.close();
	}

}
