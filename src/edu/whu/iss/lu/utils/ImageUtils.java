package edu.whu.iss.lu.utils;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.whu.iss.sd.bean.HibernateUtils;

public class ImageUtils {
	private static final String UPDATE_IMAGE_URL = "update from Parent p set p.imageUrl = :url where p.id = :userid";
	
	public static void updateImage(String parentId,String imageUrl) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Query query = session.createQuery(UPDATE_IMAGE_URL);
		query.setParameter("userid", Integer.parseInt(parentId));
		query.setParameter("url",imageUrl);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
