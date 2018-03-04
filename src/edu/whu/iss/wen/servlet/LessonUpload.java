package edu.whu.iss.wen.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.whu.iss.wen.bean.Chapter;
import edu.whu.iss.wen.bean.Lesson;
import edu.whu.iss.wen.result.ForIdResult;
import edu.whu.iss.wen.service.LessonService;
import edu.whu.iss.wen.utils.GsonUtil;
import edu.whu.iss.wen.utils.HibernateUtil;


public class LessonUpload extends HttpServlet {
	private static final String UPLOAD_DIRECTORY = "video";
	private static final int MAX_FILE_SIZE = 100 * 1024 * 1024;
	private static final int MEMORY_THRESHOLD = 3 * 1024 * 1024;
	private static final int MAX_REQUEST_SIZE = 110 * 1024 * 1024;
	String lessonName;
	String knowledgePoint;
	String description;
	String videoURL;
	String relativeURL;
	Chapter chapter;
	String chapterId;

	// private final String FIND_COLLEGESTUDENT =
	// "from Lesson lesson where lesson.id = :lessonId";
	/**
	 * Constructor of the object.
	 */
	public LessonUpload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ForIdResult universalResult=new ForIdResult();
		int maxSize = 100 * 1024 * 1024;
		PrintWriter out = response.getWriter();
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			factory.setRepository(new File("c:/tempvideos"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			String uploadPathString = request.getServletContext().getRealPath(
					"")
					+ File.separator;
			System.out.println("upload video url = " + uploadPathString);
			try {
				List<FileItem> formItems = upload.parseRequest(request);
//				Session session = HibernateUtil.openSession();
//				session.beginTransaction();
				for (FileItem fileItem : formItems) {

					if (fileItem.isFormField()) {
						String lessonInfo = fileItem.getString();
						String[] infoString=lessonInfo.substring(1,lessonInfo.length()-1).split("_");
						lessonName=infoString[0];
						knowledgePoint=infoString[1];
						description=infoString[2];
						chapterId=infoString[3];
					} else {
						File uploadDirFile = new File(uploadPathString + "\\"+UPLOAD_DIRECTORY+"\\"
								+ chapterId);
						if (!uploadDirFile.exists()) {
							uploadDirFile.mkdir();
						}
						
//						Query query = session.createQuery(FIND_COLLEGESTUDENT);
//						query.setParameter("lessonId",
//								lessonId.substring(1, lessonId.length() - 1));
//						lesson = (Lesson) query.uniqueResult();
						
						String name = fileItem.getFieldName();
						InputStream inputStream = fileItem.getInputStream();
						
						relativeURL="video"+"/"+chapterId+"/"
								+ UUID.randomUUID().toString()+
								 name.substring(name.lastIndexOf("."));
						videoURL = uploadPathString + relativeURL;

						try {
							inputStream2File(inputStream, videoURL);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
				
				Session session = HibernateUtil.getCurrentSession();
				Transaction transaction = session.beginTransaction();
				Chapter chapter=session.get(Chapter.class,Integer.parseInt(chapterId));
				transaction.commit();
				
				Lesson lesson=new Lesson();
				lesson.setLessonName(lessonName);
				lesson.setKnowledgePoint(knowledgePoint);
				lesson.setDescription(description);
				lesson.setChapter(chapter);
				
				lesson.setVideoURL(relativeURL);
				LessonService lessonService=new LessonService();
				universalResult=lessonService.lessonUploadInService(lesson);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				String s=GsonUtil.toJson(universalResult);
				out.print(s);
			}

		}
		String s=GsonUtil.toJson(universalResult);
		out.print(s);

		out.flush();
		out.close();
	}

	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath)
			throws Exception {
		System.out.println("the file path is  :" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int len;
		byte[] buffer = new byte[1024];
		while ((len = fis.read(buffer)) != -1) {
			fos.write(buffer,0,len);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();
		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
