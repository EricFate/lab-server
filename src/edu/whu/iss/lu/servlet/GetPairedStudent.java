package edu.whu.iss.lu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.bean.Student;
import edu.whu.iss.lu.bean.Parent;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;

/**
 * Servlet implementation class GetPairedStudent
 */
@WebServlet("/lu/GetPairedStudent")
public class GetPairedStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPairedStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("parentId");
		if(id!=null){
			Session session = HibernateUtils.openSession();
			Parent parent=null;
			try{
				session.beginTransaction();
				Query query = session.createQuery("from Parent p where p.id=:pid");
				query.setParameter("pid", Integer.parseInt(id));
				parent = (Parent)query.uniqueResult();
				Hibernate.initialize(parent.getStudents());

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.getTransaction().commit();
				session.close();
			}
			if(parent!=null){
				Set<Student> students = parent.getStudents();
				for(Iterator<Student> iterator=students.iterator();iterator.hasNext();){
					Student student = iterator.next();
					student.setAdminClass(null);
					student.setParent(null);
					student.setCollegeStudent(null);
					student.setCourses(null);
					student.setIssues(null);
					student.setRanks(null);
					student.setLearnings(null);
					student.setTotalLearnings(null);
					student.setExCateDetails(null);
					student.setExDetails(null);
					student.setTotalMessages(null);
				}
				out.print(GsonUtil.toJson(students));
			}else{
				out.print("fail, no such parent");
			}
		}else{
			out.print("fail,no aid");
		}

		out.flush();
		out.close();
	}

}
