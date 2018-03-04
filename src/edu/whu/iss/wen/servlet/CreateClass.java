package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.service.ClassService;
import edu.whu.iss.wen.utils.HibernateUtil;

public class CreateClass extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreateClass() {
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
		response.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();
		String id = map.get("tid")[0];
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Teacher teacher=session.get(Teacher.class, Integer.parseInt(id));
		transaction.commit();
		
		AdminClass ac=new AdminClass();
		ac.setTeacher(teacher);
		try {
			BeanUtils.populate(ac, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClassService cs =new ClassService();
		ForIdResult fir=cs.createClassInService(ac);
		Gson gson=new Gson();
		String json=gson.toJson(fir);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		
	}

}
