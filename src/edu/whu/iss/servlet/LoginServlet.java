package edu.whu.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.iss.bean.Result;
import edu.whu.iss.service.UserService;
import edu.whu.iss.utils.GsonUtil;


public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String QUERY_SQL="SELECT * FROM t_students WHERE username =? AND password =?";

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
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Result result  = UserService.login(username, password);

		writer.write(GsonUtil.toJson(result));
		writer.flush();
		writer.close();
//		Connection conn = JDBCUtil.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			 ps = conn.prepareStatement(QUERY_SQL);
//			ps.setString(1, username);
//			ps.setString(2, password);
//			 rs = ps.executeQuery();
//			if(rs.next()){
//				
//				writer.write("{\"code\":0,\"message\":\"success\"}");
//				writer.flush();
//			}else {
//				
//				writer.write("{\"code\":1,\"message\":\"”√ªßªÚ√‹¬Î¥ÌŒÛ\"}");
//				writer.flush();
//			}
//			
//			
//		} catch (SQLException e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}finally{
//			JDBCUtil.close(conn, ps, rs);
//		}
	}

}
