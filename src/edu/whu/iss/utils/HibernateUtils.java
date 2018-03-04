package edu.whu.iss.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
		Configuration configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}
	public static Session getSession(){
		 return sessionFactory.openSession();
	}
	public static Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
}
