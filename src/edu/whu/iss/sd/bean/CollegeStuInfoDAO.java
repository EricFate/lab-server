package edu.whu.iss.sd.bean;


import org.hibernate.Query;
import org.hibernate.Session;

public class CollegeStuInfoDAO {
	public static CollegeStudent getCollegeStuInfo(String id){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		String FIND_COLLEGESTUDENT = "From CollegeStudent c where c.id=:userid";
		Query query = session.createQuery(FIND_COLLEGESTUDENT);
		query.setParameter("userid", id);
		CollegeStudent collegeStudent =(CollegeStudent) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		if(collegeStudent==null)
			return null;
		collegeStudent.setAdminClass(null);
		collegeStudent.setQuestions(null);
		return collegeStudent;
		
	}

}
