package edu.whu.iss.wen.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static Gson gson;
	static{
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
	public static <T> T fromJson(String json,Type type){
		return gson.fromJson(json, type);
	}

}
