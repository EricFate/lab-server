package edu.whu.iss.sd.servlet;

import edu.whu.iss.sd.bean.CollegeStudent;
import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.sd.bean.HibernateUtils;
import edu.whu.iss.sd.bean.Video;

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
import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;

public class AddVideoServlet extends HttpServlet {
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int MAX_FILE_SIZE = 100*1024*1024;
	private static final int MEMORY_THRESHOLD = 3*1024*1024;
	private static final int MAX_REQUEST_SIZE = 110*1024*1024;
	private final String FIND_COLLEGESTUDENT = "from CollegeStudent c where c.id = :userId";

	/**
	 * Constructor of the object.
	 */
	public AddVideoServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String storedPath = "c:/tempvideos";
		int userId = 0;
		int maxSize = 100*1024*1024;
		PrintWriter out = response.getWriter();
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			factory.setRepository(new File("c:/tempvideos"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			String uploadPathString = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
			System.out.println("upload video url = "+uploadPathString);
			Session session = HibernateUtils.openSession();
			try {
				List<FileItem> formItems = upload.parseRequest(request);
				session.beginTransaction();
				CollegeStudent collegeStudent=null;
				if(formItems.size()==3){
					FileItem fileItem1 = formItems.get(0);
					String tempString = fileItem1.getString();
					userId = Integer.parseInt(tempString.substring(1,tempString.length()-1));
					FileItem fileItem = formItems.get(1);
					FileItem imageItem = formItems.get(2);
					String imageUrl = storeImage(imageItem);
					System.out.println("imageItem"+imageItem.getSize());
					File uploadDirFile = new File(uploadPathString+"/"+userId);
					if(!uploadDirFile.exists()){
						uploadDirFile.mkdir();
					}
					Query query = session.createQuery(FIND_COLLEGESTUDENT);
					query.setParameter("userId", userId);
					collegeStudent = (CollegeStudent)query.uniqueResult();
					String name = fileItem.getFieldName();
					String type = name.substring(name.indexOf("."),name.length());
					InputStream inputStream = fileItem.getInputStream();
					System.out.println("the current name is "+name);
					String uuid = UUID.randomUUID().toString();
					String videoUrl = "upload/"+ userId+"/"+uuid+type;
					String savePathString = "\\"+userId+"\\"+uuid+type;
					Video video = new Video();
					video.setVideoTitle(name.substring(0,name.indexOf('.')));
					video.setVideoUrl(videoUrl);
					video.setCollegeStudent(collegeStudent);
					video.setPlaynumber(0);
					video.setImageUrl(imageUrl);
					session.save(video);
					System.out.println("current id is "+collegeStudent.getId());

					try {
						inputStream2File(inputStream,uploadPathString+savePathString);
					} catch (Exception e) {
							// TODO: handle exception
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.print("failure");
			}finally{
				session.getTransaction().commit();
				session.close();

			}
		}

		out.print(GsonUtil.toJson("success"));


		out.flush();
		out.close();
	}


    // ��ת�����ַ���
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }
 // ��ת�����ļ�
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
 	public String storeImage(FileItem fileItem){

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		InputStream inputStream = null;
		FileOutputStream fos=null;
		String imageUrl = null;
		try {
			inputStream = fileItem.getInputStream();
			imageUrl="image/"+UUID.randomUUID().toString()+".jpg";
			String path = getServletContext().getRealPath("/")+imageUrl;
			System.out.println("imageUrl = "+imageUrl);
			File file = new File(path);
			file.createNewFile();
			fos = new FileOutputStream(file);
			IOUtils.copy(inputStream, fos);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
		return imageUrl;
 	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
