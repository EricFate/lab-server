package edu.whu.iss.sd.bean;

import org.hibernate.Query;
import org.hibernate.Session;



public class CollegeStudentUtils {
	private static final String UPDATE_IMAGE_URL = "update from CollegeStudent s set s.imageURL = :url where s.id = :userid";
	
	public static void updateImage(String collegeStudentId,String imageUrl) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Query query = session.createQuery(UPDATE_IMAGE_URL);
		query.setParameter("userid", Integer.parseInt(collegeStudentId));
		query.setParameter("url",imageUrl);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
