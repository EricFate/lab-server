package edu.whu.iss.sd.bean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
//	private static SessionFactory sessionFactory;
//	static{
//		Configuration configure = new Configuration().configure();
//		sessionFactory = configure.buildSessionFactory();
//
//	}
	public static Session openSession(){
		return edu.whu.iss.utils.HibernateUtils.getSession();
	}
		
}
