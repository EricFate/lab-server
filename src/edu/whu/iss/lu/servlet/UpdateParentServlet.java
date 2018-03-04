package edu.whu.iss.lu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.whu.iss.lu.bean.Parent;
import edu.whu.iss.utils.HibernateUtils;


/**
 * Servlet implementation class UpdateParentServlet
 */
@WebServlet("/lu/UpdateParentServlet")
public class UpdateParentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FIND_COLLEGESTUDENT="from Parent s where s.id= :userid";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateParentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Session session = HibernateUtils.getSession();
		session.beginTransaction();
		Query query = session.createQuery(FIND_COLLEGESTUDENT);
		Parent parent = null;
		int id = Integer.parseInt(request.getParameter("id"));
		if(id!=-1){
			System.out.println("UPDATEPARENTSUCCESS"+id);
			query.setParameter("userid",id);
			parent=(Parent)query.uniqueResult();
		}
		if(request.getParameter("nickname")!=null){
			parent.setNickname(request.getParameter("nickname"));
		}
		if(request.getParameter("password")!=null){
			parent.setPassword(request.getParameter("password"));
		}
		if(request.getParameter("region")!=null){
			parent.setRegion(request.getParameter("region"));
			
		}
		if(request.getParameter("signature")!=null){
			parent.setSignature(request.getParameter("signature"));
			
		}
		if(request.getParameter("imageURL")!=null){
			parent.setImageUrl(request.getParameter("imageURL"));
		}
		if(request.getParameter("gender")!=null){
			System.out.println("gender: "+request.getParameter("gender"));
			parent.setGender(request.getParameter("gender"));
		}

		if(request.getParameter("email")!=null){
			parent.setIntroduction(request.getParameter("email"));
		}
		if(request.getParameter("number")!=null){
			parent.setIntroduction(request.getParameter("number"));
		}
		if(request.getParameter("realame")!=null){
			parent.setIntroduction(request.getParameter("realame"));
		}
		if(request.getParameter("phone")!=null){
			parent.setIntroduction(request.getParameter("phone"));
		}
		if(request.getParameter("token")!=null){
			parent.setIntroduction(request.getParameter("token"));
		}
		if(request.getParameter("identity")!=null){
			parent.setIntroduction(request.getParameter("identity"));
		}
		if(request.getParameter("username")!=null){
			parent.setIntroduction(request.getParameter("username"));
		}
		if(request.getParameter("introduction")!=null){
			System.out.println("introduction: "+request.getParameter("introduction"));
			parent.setIntroduction(request.getParameter("introduction"));
		}
		session.update(parent);
		session.getTransaction().commit();
		session.close();
		PrintWriter out = response.getWriter();
		out.print("success");
		out.flush();
		out.close();
	}

}
