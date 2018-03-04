package edu.whu.iss.wen.servlet;

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

import com.google.gson.Gson;

import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.service.TeacherService;

public class FillInBlankExerciseImageServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public FillInBlankExerciseImageServlet() {
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
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		System.out.println("Upload image servlet invoked.");
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		InputStream inputStream=null;
		FileOutputStream fos=null;
		String uid=null;
		String imageURL=null;
		try{
			List<FileItem> fileItems=upload.parseRequest(request);
			for(FileItem fileItem:fileItems){
				if(fileItem.isFormField()){
					uid=fileItem.getString();
				}
				else {
					inputStream=fileItem.getInputStream();
				}
			}
			uid=uid.substring(1,uid.length()-1);
			imageURL="image/"+uid+UUID.randomUUID().toString()+".png";
			System.out.println(imageURL);
			String path=getServletContext().getRealPath("/")+imageURL;
			File file=new File(path);
			file.createNewFile();
			fos=new FileOutputStream(file);
			IOUtils.copy(inputStream, fos);
			TeacherService teacherService=new TeacherService();
			UniversalResult imageResult=new UniversalResult();
			imageResult=teacherService.fillInBlankExerciseImageUpload(uid, imageURL);
			
			Gson gson=new Gson();
			PrintWriter out=response.getWriter();
			out.print(gson.toJson(imageResult));
			out.flush();
			out.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			inputStream.close();
			fos.close();
		}
	
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
