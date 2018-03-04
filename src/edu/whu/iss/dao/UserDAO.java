package edu.whu.iss.dao;

import io.rong.RongCloud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.FriendRequest;
import edu.whu.iss.bean.Info;
import edu.whu.iss.bean.InfoDetail;
import edu.whu.iss.bean.MessageRecord;
import edu.whu.iss.bean.Roster;
import edu.whu.iss.bean.RosterGroup;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.TotalMessageRecord;
import edu.whu.iss.constant.Constants;
import edu.whu.iss.lu.bean.Parent;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.utils.RongCloudUtils;
import edu.whu.iss.wen.bean.Teacher;

public class UserDAO {
	// private final static String REGIST_SQL =
	// "INSERT INTO t_students (username,password,nickname,region,realname,class,school,stu_number,phone,email) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final static String GET_STUDENT_HQL = "from Student where username = :username";
	private final static String GET_ROSTERGROUPS_HQL = "from RosterGroup where uno = :uno";
	private final static String GET_ROSTER_HQL = "from Roster where uid =:uid and uno = :uno";
	private final static String GET_FRIEND_REQUEST_HQL = "from FriendRequest where uidFrom =:from and uidTo = :to";
	private final static String GET_ROSTERS_HQL = "from Roster where uno = :uno";
	private final static String GET_ROSTERGROUP_HQL = "from RosterGroup where name =:name and uno = :uno";
	private final static String GET_REQUEST_COUNT_HQL = "select COUNT(r) from FriendRequest r where uidTo = :uid";
	private final static String GET_REGION_GROUP_BY_NAME_HQL = "from ChatGroup where name = :name";
	private final static String GET_REGION_GROUPS_BY_UID_HQL = "select c from ChatGroup c join c.userGroups g where g.uid = :uid";
	private final static String QUERY_STUDENT_HQL = "from Student where username like :query1 or nickname like :query2";
	private final static String QUERY_TEACHER_HQL = "from Teacher where username like :query1 or realname like :query2";
	private final static String QUERY_COLLEGE_HQL = "from CollegeStudent where username like :query1 or nickname like :query2";
	private final static String QUERY_PARENT_HQL = "from Parent where username like :query1 or nickname like :query2";
	private final static String QUERY_FRIEND_REQUEST_HQL = "select new FriendRequest(id,uidFrom,nickname,imageURL,message) from FriendRequest where uidTo = :uid";
	
	private final static String UPDATE_ROSTERS_SIGNATURE_HQL = "update Roster r set r.signature=:content where r.uid=:uid";
	private final static String UPDATE_ROSTERS_IMAGEURL_HQL = "update Roster r set r.imageURL=:content where r.uid=:uid";

	private static final String GET_MESSAGE_RECORD_BY_INFO_HQL = "from MessageRecord where fromUid=:fromUid and toUid=:toUid and time=:time";
	private static final String GET_TOTAL_MESSAGE_RECORD_BY_INFO_HQL = "from TotalMessageRecord where fromUid=:fromUid and toUid=:toUid";
	
	private final static String UPDATE_IMAGEURL = "UPDATE t_students SET imageURL = ? WHERE username = ?";
	private final static String UPDATE_SIGNITURE = "UPDATE t_students SET signiture = ? WHERE username = ?";

	public static int registUser(Student user) {
		Session session = HibernateUtils.getCurrentSession();
		int id = (Integer) session.save(user);
		return id;
	}

	public static Student getUser(String username) {
		Session session = HibernateUtils.getCurrentSession();
		Query query = session.createQuery(GET_STUDENT_HQL);
		query.setParameter("username", username);
		Student user = (Student) query.uniqueResult();

		// transaction.commit();
		// session.close();
		return user;
	}

	public static void updateImageURL(String uid, String imageURL) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		RongCloud rongCloud = RongCloudUtils.getInstance();
		try {
			int id = Integer.parseInt(uid.substring(1));
			switch (uid.charAt(0)) {
			case 's':
				Student s = session.get(Student.class, id);
				s.setImageURL(imageURL);
				rongCloud.user.refresh(uid, s.getNickname(),
						Constants.SERVER_URL + imageURL);
				break;
			default:
				break;
			}
			int update = session.createQuery(UPDATE_ROSTERS_IMAGEURL_HQL)
					.setString("content", imageURL).setString("uid", uid)
					.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.commit();
	}

	public static void updateSigniture(String uid, String signiture) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		int id = Integer.parseInt(uid.substring(1));
		switch (uid.charAt(0)) {
		case 's':
			Student s = session.get(Student.class, id);
			s.setSignature(signiture);
			break;

		default:
			break;
		}
		int update = session.createQuery(UPDATE_ROSTERS_SIGNATURE_HQL)
				.setString("content", signiture).setString("uid", uid)
				.executeUpdate();
		transaction.commit();
	}

	public static void updateNickname(String uid, String nickname) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		int id = Integer.parseInt(uid.substring(1));
		switch (uid.charAt(0)) {
		case 's':

			Student s = session.get(Student.class, id);
			s.setNickname(nickname);
			break;

		default:
			break;
		}
		transaction.commit();
	}

	public static List<RosterGroup> getRosterGroups(String quid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		List<RosterGroup> list = session.createQuery(GET_ROSTERGROUPS_HQL)
				.setString("uno", quid).list();

		return list;
	}

	public static Roster getRoster(String quid, String muid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Roster roster = (Roster) session.createQuery(GET_ROSTER_HQL)
				.setString("uid", quid).setString("uno", muid).uniqueResult();
		return roster;
	}

	public static List<Info> queryContacts(String query) {
		// TODO Auto-generated method stub

		List<Info> infos = new ArrayList<Info>();
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery(QUERY_STUDENT_HQL)
				.setString("query1", "%" + query + "%")
				.setString("query2", "%" + query + "%").list();
		for (Student student : students) {
			infos.add(new Info("s" + student.getId(), student.getUsername(),
					student.getNickname(), student.getImageURL(), student
							.getSignature()));
		}
		List<Teacher> teachers = session.createQuery(QUERY_TEACHER_HQL)
				.setString("query1", "%" + query + "%")
				.setString("query2", "%" + query + "%").list();
		for (Teacher t : teachers) {
			infos.add(new Info("t" + t.getId(), t.getUsername(),
					t.getRealname(), t.getImageURL(), t
							.getSignature()));
		}
		List<CollegeStudent> collegeStudents = session.createQuery(QUERY_COLLEGE_HQL)
				.setString("query1", "%" + query + "%")
				.setString("query2", "%" + query + "%").list();
		for (CollegeStudent t : collegeStudents) {
			infos.add(new Info("c" + t.getId(), t.getUsername(),
					t.getNickname(), t.getImageURL(), t
							.getSignature()));
		}
		List<Parent> parents = session.createQuery(QUERY_PARENT_HQL)
				.setString("query1", "%" + query + "%")
				.setString("query2", "%" + query + "%").list();
		for (Parent t : parents) {
			infos.add(new Info("a" + t.getId(), t.getUsername(),
					t.getNickname(), t.getImageUrl(), t
							.getSignature()));
		}
		transaction.commit();
		System.out.println("infos"+infos.size());
		return infos;
	}

	public static Info getUserInfoByUid(String quid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		int id;
		Info info = null;
		try {
			id = Integer.parseInt(quid.substring(1));
			switch (quid.charAt(0)) {
			case 's':
				Student student = session.get(Student.class, id);
				if(student!=null)
				info = new Info(quid, student.getNickname(),
						student.getImageURL(), student.getSignature());
				break;
			case 't':
				Teacher teacher = session.get(Teacher.class, id);
				if(teacher!=null)
				info = new Info(quid, teacher.getRealname(),
						teacher.getImageURL(), teacher.getSignature());
				break;
			case 'c':
				CollegeStudent collegeStudent = session.get(CollegeStudent.class, id);
				if(collegeStudent!=null)
				info = new Info(quid, collegeStudent.getNickname(),
						collegeStudent.getImageURL(), collegeStudent.getSignature());
				break;
			case 'a':
				Parent p = session.get(Parent.class, id);
				if(p!=null)
				info = new Info(quid, p.getNickname(),
						p.getImageUrl(), p.getSignature());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("非本服务器成员");
		}
		return info;
	}

	public static RosterGroup getRosterGroup(String group, String muid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		RosterGroup rosterGroup = (RosterGroup) session
				.createQuery(GET_ROSTERGROUP_HQL).setString("name", group)
				.setString("uno", muid).uniqueResult();
		return rosterGroup;
	}

	public static List<Roster> getRosters(String uid) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Roster> rosters = session.createQuery(GET_ROSTERS_HQL)
				.setString("uno", uid).list();
		transaction.commit();
		return rosters;
	}

	public static List<FriendRequest> getFriendRequest(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		List<FriendRequest> requests = session.createQuery(QUERY_FRIEND_REQUEST_HQL).setString("uid", uid).list();
		return requests;
	}

	public static InfoDetail getDetailedInfoByUid(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		InfoDetail infoDetail = new InfoDetail();
		try {
			
			int id = Integer.parseInt(uid.substring(1));
			switch (uid.charAt(0)) {
			case 's':
				Student student = session.get(Student.class, id);
				BeanUtils.copyProperties(infoDetail, student);
				break;
			case 't':
				Teacher teacher = session.get(Teacher.class, id);
				BeanUtils.copyProperties(infoDetail, teacher);
				break;
			case 'c':
				CollegeStudent colleger = session.get(CollegeStudent.class, id);
				BeanUtils.copyProperties(infoDetail, colleger);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		transaction.commit();
		return infoDetail;
	}

	public static int getRequestCount(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		int count = ((Long) session.createQuery(GET_REQUEST_COUNT_HQL).setString("uid", uid).uniqueResult()).intValue();
		transaction.commit();
		return count;
	}

	public static FriendRequest getFriendRequestByName(String uidFrom, String uidTo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		FriendRequest request = (FriendRequest) session.createQuery(GET_FRIEND_REQUEST_HQL).setString("from", uidFrom)
		.setString("to", uidTo).uniqueResult();
		return request;
	}

	public static MessageRecord getMessageRecordByInfo(String from,String to) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		MessageRecord record = (MessageRecord) session.createQuery(GET_MESSAGE_RECORD_BY_INFO_HQL).setString("fromUid", from)
		.setString("toUid", to).setDate("time", new Date()).uniqueResult();
		return record;
	}
	public static TotalMessageRecord getTotalCourseLearningByInfo(String from,String to) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		TotalMessageRecord record = (TotalMessageRecord) session.createQuery(GET_TOTAL_MESSAGE_RECORD_BY_INFO_HQL).setString("fromUid", from)
				.setString("toUid", to).uniqueResult();
		return record;
	}

	public static ChatGroup getGroupByName(String region) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		ChatGroup chatGroup = (ChatGroup) session.createQuery(GET_REGION_GROUP_BY_NAME_HQL)
		.setString("name",region).uniqueResult();
		return chatGroup;
	}

	public static List<ChatGroup> getGroupsByUid(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		List<ChatGroup> list = session.createQuery(GET_REGION_GROUPS_BY_UID_HQL).setString("uid", uid).list();
		return list;
	}

}
