package com.jetbaba.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;
import com.jayway.jsonpath.JsonPath;

/**
 * 
 * @author jet
 *
 * 读取配置文件到类
 */
public class Global {
	
//	public static void main(String[] args) {
//		 Global.class.getclass
//	}
//	
	/**
	 * 
	 * @param key
	 * @return
	 * 
	 * 读取配置文件
	 */
	public static String getValueByKey(String key) {
		Properties p = new Properties();
		
		InputStream is = Global.class.getResourceAsStream("/configuration.properties");
		
		try {
			p.load(is);
			return p.getProperty(key);
		} catch (IOException e) {
			/**
			 * 异常处理后期再做
			 */
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println(Global.getValueByKey("doubanmovie.year"));
//		Pattern p = Pattern.compile("subject/(\\d+)/");
//		Matcher m = p.matcher("https://movie.douban.com/subject/2174535/?from=subject-page");
//		
//		while(m.find())
//			System.out.println(m.group(1));
		
		/**
		 * 发送http请求获取Json
		 */
		String jsonResultBody = HttpRequest.get("https://api.douban.com/v2/movie/subject/2174535").body();
		/**
		 * 使用jsonPath解析并对比
		 */
//		String jpForScore = "$.rating.average";
//		Double mScore = JsonPath.read(jsonResultBody, jpForScore);
//		String jpForYear = "$.year";
//		String mYear = JsonPath.read(jsonResultBody, jpForYear);
//		String jpForCountry = "$.countries";
//		String mCountries = JsonPath.read(jsonResultBody, jpForCountry).toString();
//		
//		System.out.println(mScore + " "  + mYear + " " + mCountries);
//	}
}
