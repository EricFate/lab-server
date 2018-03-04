package edu.whu.iss.lu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.bean.RosterGroup;
import edu.whu.iss.lu.bean.Parent;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.utils.RongCloudUtils;
import io.rong.models.TokenReslut;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/lu/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FIND_Parent_ACCOUNT = "FROM Parent p WHERE p.username = :name";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
	//		if(request==null)
	//			return;
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println("register get request");
			System.out.println("get sign up data"+request.getParameter("username")+request.getParameter("password"));
			PrintWriter out = response.getWriter();
			Session tempSession=HibernateUtils.getSession();
			tempSession.beginTransaction();
			Query query = tempSession.createQuery(FIND_Parent_ACCOUNT);
			query.setParameter("name", request.getParameter("username"));
			Parent tempParent = (Parent)query.uniqueResult();
			tempSession.getTransaction().commit();
			tempSession.close();
			if (tempParent==null){
				Parent parent = new Parent();
				String username = request.getParameter("username");
				parent.setUsername(request.getParameter("username"));
				parent.setPassword(request.getParameter("password"));
				Session session = HibernateUtils.getSession();
				session.beginTransaction();
				int id = (Integer) session.save(parent);
				String uid = "a" + id;
				RosterGroup rosterGroup = new RosterGroup("Î´·Ö×é", uid);
				session.save(rosterGroup);
				try {
					TokenReslut token = RongCloudUtils.getInstance().user.getToken("a"+id, username, "");
					parent.setToken(token.getToken());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.getTransaction().commit();
				session.close();
				out.print("true");
				}else{
				out.print("account already exits");			
				}
				out.flush();
				out.close();
		}

}
