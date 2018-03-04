package edu.whu.iss.wen.utils;

import edu.whu.iss.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//	private static SessionFactory sessionFactory;
//	static{
//		Configuration configure = new Configuration().configure();
//		sessionFactory = configure.buildSessionFactory();
//
//	}
	public static Session openSession(){
		return HibernateUtils.getSession();
	}
	public static Session getCurrentSession(){
		return HibernateUtils.getCurrentSession();
	}
}
