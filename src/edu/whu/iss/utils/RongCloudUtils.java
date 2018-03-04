package edu.whu.iss.utils;

import edu.whu.iss.constant.Constants;
import io.rong.RongCloud;

public class RongCloudUtils {
	public static RongCloud rongCloud;
	static{
		rongCloud = RongCloud.getInstance(Constants.APP_KET, Constants.SECRET);
	}
	public static RongCloud getInstance(){
		return rongCloud;
	}
}
