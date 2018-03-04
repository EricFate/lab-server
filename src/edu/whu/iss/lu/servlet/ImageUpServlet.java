package edu.whu.iss.lu.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import edu.whu.iss.lu.utils.ImageUtils;
import edu.whu.iss.sd.bean.CollegeStudentUtils;

public class ImageUpServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public ImageUpServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		InputStream inputStream = null;
		FileOutputStream fos=null;
		String uid = null;
		String imageUrl = null;
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem fileItem:fileItems){
				if(fileItem.isFormField()){
					uid=fileItem.getString();
				}else{
					inputStream = fileItem.getInputStream();
				}
			}
			System.out.println("uid = "+uid);
//			uid = uid.substring(1,uid.length()-1);
			System.out.println("user id is "+uid);
			imageUrl="image/"+uid+".jpg";
			String path = getServletContext().getRealPath("/")+imageUrl;
			System.out.println("imageUrl = "+imageUrl);
			File file = new File(path);
			file.createNewFile();
			fos = new FileOutputStream(file);
			IOUtils.copy(inputStream, fos);
			ImageUtils.updateImage(uid, imageUrl);
			writer.write(imageUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		writer.flush();
		writer.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
