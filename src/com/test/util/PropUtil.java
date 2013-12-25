package com.test.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {
	private static Properties prop = new Properties();
	private static long last_load_time = 0L;
	public static void load(){
		try {
			InputStream is = null;
			is = PropUtil.class.getResourceAsStream("db.properties");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void load1(){
		try {
			InputStream is = null;
			is = PropUtil.class.getResourceAsStream("/db1.properties");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void load2(){
		try {
			InputStream is = null;
			is = new FileInputStream(new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"db2.properties"));
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String get(String key){
		if(System.currentTimeMillis() - last_load_time > 60 * 1000){
			load();
		}
		return prop.getProperty(key);
	}
	public static int getInt(String key){
		String val = get(key);
		return Integer.parseInt(val);
	}
	public static void main(String[] args){
		/**
		 * 可以深入jar包进行文件查找
		 */
		System.out.println(PropUtil.class.getClassLoader().getResource("com/test/util/db.properties"));
		System.out.println(PropUtil.class.getResource("/db1.properties"));
		PropUtil.load1();
		String ss = PropUtil.get("serverIp");
		System.out.println(ss+".");
	}
}
