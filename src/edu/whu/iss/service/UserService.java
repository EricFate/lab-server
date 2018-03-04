package edu.whu.iss.service;

import io.rong.RongCloud;
import io.rong.models.TokenReslut;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.bean.ChatGroup;
import edu.whu.iss.bean.FriendRequest;
import edu.whu.iss.bean.Info;
import edu.whu.iss.bean.InfoDetail;
import edu.whu.iss.bean.MessageRecord;
import edu.whu.iss.bean.Result;
import edu.whu.iss.bean.ResultItem;
import edu.whu.iss.bean.Roster;
import edu.whu.iss.bean.RosterGroup;
import edu.whu.iss.bean.Student;
import edu.whu.iss.bean.TotalMessageRecord;
import edu.whu.iss.bean.UserGroup;
import edu.whu.iss.dao.UserDAO;
import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.utils.HibernateUtils;
import edu.whu.iss.utils.RongCloudUtils;
import edu.whu.iss.wen.bean.AdminClass;
import edu.whu.iss.wen.bean.Course;
import edu.whu.iss.wen.bean.Notice;
import edu.whu.iss.wen.bean.Teacher;
import exceptions.AccountException;

public class UserService {
	public static void registUser(Student user) throws AccountException {
		// return UserDAO.registUser(user);
		Session session = HibernateUtils.getCurrentSession();
		String nickname = user.getNickname();
		if (nickname.trim().equals(""))
			user.setNickname(user.getUsername());
		Transaction transaction = session.beginTransaction();
		Student u = UserDAO.getUser(user.getUsername());
		if(u!=null) throw new AccountException("用户已存在");
		int id = UserDAO.registUser(user);
			
		String uid = "s" + id;
		RosterGroup rosterGroup = new RosterGroup("未分组", uid);
		session.save(rosterGroup);
		String region = user.getRegion().replace("_", "")+"群";
		ChatGroup group = UserDAO.getGroupByName(region);
		if (group==null){
			group = new ChatGroup();
			group.setName(region);
			group.setUserGroups(new HashSet<UserGroup>());
		}
		UserGroup userGroup = new UserGroup();
		userGroup.setUid("s"+id);
		session.save(userGroup);
		group.getUserGroups().add(userGroup);
		int gid = (Integer) session.save(group);
		try {
			RongCloud instance = RongCloudUtils.getInstance();
			TokenReslut token = instance.user.getToken(uid,
					user.getNickname(), "");
			if (token.getCode() != 200) {
				System.out.println(token.getErrorMessage());
			} else {
				System.out.println(token.getUserId());
				user.setToken(token.getToken());
			}
			instance.group.join(new String[]{"s"+id},"g"+ gid, region);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.commit();

	}

	public static Result login(String username, String password) {
		// return UserDAO.login(username,password);
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student user = UserDAO.getUser(username);
		transaction.commit();
		Result result = null;
		if (user == null) {
			result = new Result(1, "用户不存在");
		} else if (user.getPassword().equals(password)) {
			result = new Result(0, "成功", user.getId(), username,
					user.getNickname(), user.getImageURL(),
					user.getSignature(), user.getToken(),user.getGrade());
		} else {
			result = new Result(1, "密码错误");
		}
		return result;

	}

	public static Info getUserInfo(String quid, String muid) {

		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Info info = null;
		Roster roster = UserDAO.getRoster(quid, muid);
		if (roster == null) {
			info = UserDAO.getUserInfoByUid(quid);
			if(info!=null)
				info.setSignature(null);
		} else
			info = new Info(quid, roster.getRemark(), roster.getImageURL());
		transaction.commit();
		return info;
	}

	// public static ArrayList<Group> getContacts(){
	// ArrayList<Group> groups = new ArrayList<Group>();
	//
	// return null;
	// }
	public static Result requestToken(String uid) {
		int id = Integer.parseInt(uid.substring(1));
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Info info = null;
		switch (uid.charAt(0)) {
		case 's':
			Student student = session.get(Student.class, id);
			info = new Info(uid, student.getNickname(), student.getImageURL());
			break;
		default:
			break;
		}
		transaction.commit();
		Result result;
		if (info == null)
			result = new Result(1, "数据不存在");
		else {
			TokenReslut token;
			try {
				token = RongCloudUtils.getInstance().user.getToken(uid,
						info.getNickname(), info.getImageURL());
				result = new Result(0, "成功", token.getToken());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = new Result(1, "获取错误");
			}
		}
		return result;
	}

	public static List<RosterGroup> getContactsByUid(String quid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		System.out.println(session.getTransaction()) ;
		Transaction transaction = session.beginTransaction();
		List<RosterGroup> list = UserDAO.getRosterGroups(quid);
		transaction.commit();
		for (RosterGroup rosterGroup : list) {
			Set<Roster> rosters = rosterGroup.getRosters();
			for (Roster roster : rosters) {
				roster.setGroup(null);
				roster.setUno(null);
			}
		}
		return list;
	}

	public static List<ResultItem> queryContacts(String uid, String query) {
		// TODO Auto-generated method stub
		List<Info> list = UserDAO.queryContacts(query);
		List<Roster> rosters = UserDAO.getRosters(uid);
		List<ResultItem> querylist = divideList(list, rosters, uid);
		return querylist;
	}

	private static List<ResultItem> divideList(List<Info> list,
			List<Roster> rosters, String uid) {
		// TODO Auto-generated method stub
		List<ResultItem> items = new ArrayList<ResultItem>();
		List<Info> stranges = new ArrayList<Info>();
		List<Info> friends = new ArrayList<Info>();
		for (Info info : list) {
			if (!info.getId().equals(uid)){
				if (inRoster(info, rosters)) {
					friends.add(info);
				} else {
					stranges.add(info);
				}
			}
		}
		if (friends.size() != 0)
			items.add(new ResultItem(0, "我的好友", friends));
		if (stranges.size() != 0)
			items.add(new ResultItem(1, "陌生人", stranges));
		System.out.println("items"+items.size());
		return items;
	}

	private static boolean inRoster(Info info, List<Roster> rosters) {
		for (Roster roster : rosters) {
			if (info.getId().equals(roster.getUid())) {
				info.setRemark(roster.getRemark());
				return true;
			}
		}
		return false;
	}

	public static List<String> getGroupNames(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<RosterGroup> rosterGroups = UserDAO.getRosterGroups(uid);
		transaction.commit();
		List<String> list = new ArrayList<String>();
		for (RosterGroup rosterGroup : rosterGroups) {
			list.add(rosterGroup.getName());
		}
		return list;

	}

	public static void addFriend(int rid, String group,
			String remark) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		FriendRequest friendRequest = session.get(FriendRequest.class, rid);
		if (friendRequest==null) {
			transaction.commit();
			return;
		}
		String from = friendRequest.getUidFrom();
		String to = friendRequest.getUidTo();
		String oremark = friendRequest.getRemark();
		String ogroup = friendRequest.getGroupName();
		addFriend(to, from, ogroup, oremark);
		addFriend(from, to, group, remark);
		session.delete(friendRequest);
		transaction.commit();
	}

	public static void addFriend(String quid,String muid, String group,
			String remark) {
		Session session = HibernateUtils.getCurrentSession();
		Info info = UserDAO.getUserInfoByUid(quid);
		RosterGroup rosterGroup = UserDAO.getRosterGroup(group, muid);
		if (rosterGroup == null)
			rosterGroup = new RosterGroup(group, muid);
		Roster roster = new Roster();
		roster.setGroup(rosterGroup);
		roster.setUid(quid);
		roster.setUno(muid);
		roster.setRemark(remark);
		roster.setSignature(info.getSignature());
		roster.setImageURL(info.getImageURL());
		session.save(roster);
	}

	public static void requestAddFriend(FriendRequest friendRequest) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String from = friendRequest.getUidFrom();
		FriendRequest request = UserDAO.getFriendRequestByName(friendRequest.getUidFrom(),friendRequest.getUidTo());
		if(request != null){
			request.setRemark(friendRequest.getRemark());
			request.setGroupName(friendRequest.getGroupName());
		}else {
			System.out.println("uid:"+from);
			Info info = UserDAO.getUserInfoByUid(from);
			friendRequest.setNickname(info.getNickname());
			friendRequest.setImageURL(info.getImageURL());
			session.save(friendRequest);			
		}
		transaction.commit();
	}



	public static List<FriendRequest> getFriendRequest(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<FriendRequest> request = UserDAO.getFriendRequest(uid);
		transaction.commit();
		return request;
	}

	public static InfoDetail getDetailedInfo(String uid) {
		// TODO Auto-generated method stub
		InfoDetail infoDetail = UserDAO.getDetailedInfoByUid(uid);
		return infoDetail;
	}

	public static int getRequestCount(String uid) {
		// TODO Auto-generated method stub
		return UserDAO.getRequestCount(uid);
	}

	public static void changeInfo(int id,Map<String, String[]> map) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student s =  session.get(Student.class, id);
		try {
			BeanUtils.populate(s, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.commit();
		
	}

	public static ChatGroup getGroupInfo(int gid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ChatGroup group = session.get(ChatGroup.class, gid);
		transaction.commit();
		group.setCourse(null);
		group.setUserGroups(null);
		return group;
	}

	public static void messageRecord(List<MessageRecord> records) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		for (MessageRecord record : records) {
			MessageRecord re = UserDAO.getMessageRecordByInfo(record.getFromUid(),record.getToUid());
			String fromUid = record.getFromUid();
			String toUid = record.getToUid();
			int number = record.getNumber();
			if(re == null){
				re = new MessageRecord();
				re.setFromUid(fromUid);
				re.setToUid(toUid);
				re.setTime(new Date());
			}
			re.setNumber(re.getNumber()+number);
			session.save(re);
			 TotalMessageRecord totalRe = UserDAO.getTotalCourseLearningByInfo(fromUid, toUid);
			if(totalRe==null){
				totalRe = new TotalMessageRecord();
				totalRe.setFromUid(fromUid);
				totalRe.setToUid(toUid);
			}
			totalRe.setNumber(totalRe.getNumber()+number);
			session.save(totalRe);
		}
		transaction.commit();
	}

	public static AdminClass getStudentClass(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, id);
		AdminClass adminClass = s.getAdminClass();
		System.out.println(adminClass);
		int stsize = 0;
		if (adminClass==null) {
			return null;
		}
		Set<Student> students = adminClass.getStudents();
		if (students!=null) {
			stsize =students.size();
		}
		System.out.println(stsize);
		adminClass.setsNumber(stsize);
		
		int cssize = adminClass.getCollegeStudents().size();
		System.out.println(cssize);
		adminClass.setCsNumber(cssize);
		adminClass.setCollegeStudents(null);
		adminClass.setStudents(null);
		adminClass.setRequests(null);
		adminClass.setNotices(null);
		ChatGroup chatGroup = adminClass.getChatGroup();
		chatGroup.setAdminClass(null);
		chatGroup.setCourse(null);
		chatGroup.setUserGroups(null);
		Set<Notice> notices = chatGroup.getNotices();
		for (Notice notice : notices) {
			notice.setChatGroup(null);
		}
		Teacher teacher = adminClass.getTeacher();
		adminClass.setTeacher(new Teacher(teacher.getId(),teacher.getRealname(),teacher.getImageURL()));
		Set<Course> courses = adminClass.getCourses();
		Set<Course> newCs = new HashSet<Course>();
		for (Course course : courses) {
			newCs.add(new Course(course.getId(), course.getName(),course.getFocusNumber(),course.getCoverURL(),course.getChapterNumber())) ;
		}
		adminClass.setCourses(newCs);
		session.clear();
		transaction.commit();
		return adminClass;
		
	}

	public static List<Info> getClassMember(int clid, int type) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		AdminClass clz = session.get(AdminClass.class, clid);
		List<Info> infos = new ArrayList<Info>();
		switch (type) {
		case 0:
			Set<Student> students = clz.getStudents();
			for (Student student : students) {
				infos.add(new Info("s"+student.getId(), student.getRealname(), student.getImageURL()));
			}
			break;
		case 1:
			Set<CollegeStudent> collegeStudents = clz.getCollegeStudents();
			for (CollegeStudent collegeStudent : collegeStudents) {
				infos.add(new Info("c"+collegeStudent.getId(), collegeStudent.getRealname(), collegeStudent.getImageURL()));
			}
			break;
		default:
			break;
		}
		transaction.commit();
		return infos;
	}

	public static List<ChatGroup> getGroups(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<ChatGroup> list = UserDAO.getGroupsByUid(uid);
		list = new ArrayList<ChatGroup>(list);
		transaction.commit();
		for (ChatGroup c : list) {
			c.setCourse(null);
			c.setUserGroups(null);
			c.setAdminClass(null);
			c.setNotices(null);
		}
		return list;
	}
	public static Set<Notice> getNotice(int gid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		 ChatGroup chatGroup = session.get(ChatGroup.class, gid);
		Set<Notice> notices = chatGroup.getNotices();
		transaction.commit();
		for (Notice notice : notices) {
			notice.setChatGroup(null);
		}
		return notices;
	}


}
