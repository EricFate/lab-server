package edu.whu.iss.sd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.whu.iss.bean.Student;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.wen.bean.Course;

public class GetStudentCourseInfo extends HttpServlet{
  private final String FIND_STUDENT = "from Student s where id = :userid";

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
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doPost(request, response);
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
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setCharacterEncoding("uft-8");
    PrintWriter out = response.getWriter();
    String uid = request.getParameter("uid");
    if(uid==null){
      out.flush();
      out.close();
      return;
    }
    int id = Integer.parseInt(uid.substring(1));
    Session session = HibernateUtils.openSession();
    Student student = null;
    try{
      session.beginTransaction();
      Query query = session.createQuery(FIND_STUDENT);
      query.setParameter("userid",id);
      student = (Student)query.uniqueResult();
      Hibernate.initialize(student.getCourses());

    }catch(Exception e){
      e.printStackTrace();
    }finally{
      session.getTransaction().commit();
      session.close();

    }
    Set<Course> courses = student.getCourses();
    out.print(GsonUtil.toJson(courses));
    out.flush();
    out.close();

  }

}
