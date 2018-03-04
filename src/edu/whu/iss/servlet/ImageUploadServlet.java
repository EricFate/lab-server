package edu.whu.iss.servlet;

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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import edu.whu.iss.bean.Result;
import edu.whu.iss.dao.UserDAO;
import edu.whu.iss.utils.GsonUtil;

public class ImageUploadServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		InputStream inputStream = null;
		FileOutputStream fos = null;
		String uid = null;
		String imageURL = null;
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem fileItem : fileItems){
				if(fileItem.isFormField()){
					uid = fileItem.getString();

				}else{

					inputStream = fileItem.getInputStream();
				}
			}
			uid = uid.substring(1, uid.length()-1);
			String userDir = "image/user/"+uid+"/";
			FileUtils.forceMkdir(new File(getServletContext().getRealPath("/")+userDir));
			String imageName = UUID.randomUUID().toString();
			imageURL = userDir+imageName+".jpg";
			String path = getServletContext().getRealPath("/")+imageURL;
			File file = new File(path);
			System.out.println(file.createNewFile()); 
			fos = new FileOutputStream(file);
			IOUtils.copy(inputStream, fos);
			UserDAO.updateImageURL(uid, imageURL);
			writer.write(GsonUtil.toJson(new Result(0, imageURL)));
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			writer.write(GsonUtil.toJson(new Result(1, e.getMessage())));
			e.printStackTrace();
		}finally{
			fos.close();
			writer.flush();
			writer.close();
		}
	}

}
