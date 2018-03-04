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

import com.google.gson.Gson;

import edu.whu.iss.lu.bean.Parent;
import edu.whu.iss.utils.HibernateUtils;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/lu/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FIND_Parent_ACCOUNT = "FROM Parent p WHERE p.username = :name";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("11112");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("1111");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("接收到请求");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		response.setContentType("string");
		PrintWriter out = response.getWriter();
		String username;
		String password;
		
		System.out.println("获取前端数据成功"+request.getParameter("username")+request.getParameter("password"));
		username=request.getParameter("username");
		password=request.getParameter("password");
//		Session session=HibernateUtils.openSession();
//		session.beginTransaction();
//		Query query = session.createQuery(FIND_COLLEGESTUDENT_ACCOUNT);
//		query.setParameter("name", username);
//		Parent parent = (CollegeStudent)query.uniqueResult();
//		System.out.println(collegeStudent.getGender());
//		ParentDAO parentD=new ParentDAO();  
//	     Parent parent=new Parent();  
	     //封装  
//	     parent.setUsername(username);  
//	     parent.setPassword(password);  
	     //调用方法，保存对象  
//	     parentD.save(parent);  
	     //从数据库中加载类对象  
//	     out.print("注册成功<br>");  
//	     List l=null;  
//	     l=parentD.findByProperty("username",username);  
//			session.getTransaction().commit();
//	     Iterator i=l.iterator();  
	     //out.print("葱数据库中加载类对象<br>");  
//	     if(l!=null){  
//	      parent=(Parent) l.get(0);  
//	      out.print("用户名："+m.getUsername()+"<br>");  
//	      out.print("密码："+m.getPassword()+"<br>");  
//	      out.print((new Gson()).toJson(parent));
//	      System.out.println("first success");
//	     }else{  
//	      out.print("数据库中不存在该对象");  
//	      System.out.println("another success");
//	     }  
//		session.getTransaction().commit();
//		System.out.println(collegeStudent.getGender());

//		collegeStudent.setAnswers(null);
//		collegeStudent.setVideos(null);
//		collegeStudent.setQuestions(null);
//		out.print((new Gson()).toJson(parent));
//		session.close();
		Session session=HibernateUtils.getSession();
		session.beginTransaction();
		Query query = session.createQuery(FIND_Parent_ACCOUNT);
		query.setParameter("name", username);
		Parent parent = (Parent)query.uniqueResult();
		session.getTransaction().commit();
		if (parent==null){
//			session.close();
//			out.close();
//			return;
		}
		else if(!parent.getPassword().equals(password)){
			parent.setPasswordWriteFlag(false);
//			out.print((new Gson()).toJson(parent));
		}
		else{
			parent.setPasswordWriteFlag(true);
			parent.setHasAccountFlag(true);
			parent.setStudents(null);
			System.out.println("LOGINSUCCESS"+parent.getId());
//			out.print((new Gson()).toJson(parent));
		}
		out.print((new Gson()).toJson(parent));
		session.close();
		out.flush();
		out.close();
	}
	
//	public boolean login(Person person) throwsException
//	{
//	boolean flag = false ;
//	try {
//	String hql =”FROM Parent p WHERE p.id= :id and p.password= :password” ;
//	Query q =this.session.createQuery(hql) ;
//	q.setParameter(“id”,person.getId());
//	q.setParameter(“password”,person.getPassword());
//	List l =q.list() ;
//	Iterator iter= l.iterator() ;
//	if(iter.hasNext())
//	{
//	flag= true;
//	Personp = (Person)iter.next();
//	System.out.println(p.getName());
//	person.setName(p.getName());
//	}
//	} catch (RuntimeException e){
//	e.printStackTrace();
//	}
//	return flag ;
//	}

}
