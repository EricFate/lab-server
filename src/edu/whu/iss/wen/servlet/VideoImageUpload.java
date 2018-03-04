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

import edu.whu.iss.sd.bean.GsonUtil;
import edu.whu.iss.wen.result.UniversalResult;
import edu.whu.iss.wen.service.LessonService;

public class VideoImageUpload extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public VideoImageUpload() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		InputStream inputStream=null;
		FileOutputStream fos=null;
		String id=null;
		String coverURL=null;
		try{
			List<FileItem> fileItems=upload.parseRequest(request);
			for(FileItem fileItem:fileItems){
				if(fileItem.isFormField()){
					id=fileItem.getString();
				}
				else {
					inputStream=fileItem.getInputStream();
				}
			}
			id=id.substring(1,id.length()-1);
			coverURL="videoImage/"+id+UUID.randomUUID().toString()+".jpg";
			String path=getServletContext().getRealPath("/")+coverURL;
			File file=new File(path);
			file.createNewFile();
			fos=new FileOutputStream(file);
			IOUtils.copy(inputStream, fos);
			LessonService service=new LessonService();
			UniversalResult result=service.videoImageUploadInService(Integer.parseInt(id), coverURL);
			PrintWriter out=response.getWriter();
			out.print(GsonUtil.toJson(result));
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
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
