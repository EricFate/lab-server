package edu.whu.iss.wen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Notice;
import edu.whu.iss.wen.bean.Teacher;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.service.ClassService;
import edu.whu.iss.wen.service.CourseService;
import edu.whu.iss.wen.utils.HibernateUtil;

public class NoticeUpload extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public NoticeUpload() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	@Override
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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();

		int type = Integer.parseInt(request.getParameter("type"));
		int id = Integer.parseInt(request.getParameter("courseId"));
		
		Notice notice=new Notice();
		CourseService cs =new CourseService();
		ClassService classService = new ClassService();
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		if(type==0){
			Course c = session.get(Course.class, id);
			ChatGroup cg=c.getChatGroup();
			notice.setChatGroup(cg);
		}else{
			AdminClass adminClass =session.get(AdminClass.class, id);;
			if(adminClass!=null){
				ChatGroup cg=adminClass.getChatGroup();
				notice.setChatGroup(cg);
				System.out.println("Set adminClass successfully.");
			}
			else {
				System.out.println("Failed to get adminClass.");
			}
		}
		transaction.commit();
		try {
			BeanUtils.populate(notice, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		notice.setDate(new Date());
		
		UniversalResult ur=cs.noticeUploadInService(notice, Integer.parseInt(request.getParameter("courseId")));
		Gson gson=new Gson();
		String json=gson.toJson(ur);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();

	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
