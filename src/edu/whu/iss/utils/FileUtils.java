package edu.whu.iss.utils;

import java.io.File;

public class FileUtils {
	public static void mkDir(String path){
		File file = new File(path);
		if(file.exists()){
			return;
		}else {
			file.mkdirs();
		}
	}
	
}
