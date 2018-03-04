package edu.whu.iss.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import edu.whu.iss.bean.PrivateExCateDetail;
import edu.whu.iss.bean.Student;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.wen.bean.ExerciseCatagory;


public class ExerciseDAO {
	private static final String QUERY_EX_CATE = "from PrivateExCateDetail where student.id = :id and exerciseCatagory.id in (:ecids)";
	private static final String QUERY_FINISH_COUNT = "select count(*) from privateexdetail p,exercise_category e_c where "
			+ "p.eno = e_c.eno and p.sno = :id and e_c.ecno = :ecid";
	public List<PrivateExCateDetail> getExerciseCategories(Student s,Collection<ExerciseCatagory> ecs){
		Session session = HibernateUtils.getCurrentSession();
		List<PrivateExCateDetail> list = session.createCriteria(PrivateExCateDetail.class)
		.add(Restrictions.eq("student", s))
		.add(Restrictions.in("exerciseCatagory", ecs)).list();
//		
//		List<PrivateExCateDetail> list = session.createQuery(QUERY_EX_CATE).setParameter("id", s.getId())
//		.setParameterList("ecids", ecs).list();
		return list;
	}
	public int getFinishedCount(int id,int ecid){
		Session session = HibernateUtils.getCurrentSession();
		BigInteger count =  (BigInteger) session.createSQLQuery(QUERY_FINISH_COUNT)
				.setParameter("id", id)
		.setParameter("ecid", ecid)
		.uniqueResult();
		return count.intValue();
	}
}
