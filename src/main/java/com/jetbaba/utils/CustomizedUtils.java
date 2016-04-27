package com.jetbaba.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author jet
 * 
 * 本类总结一些特殊静态工具方法
 *
 */
public class CustomizedUtils {
	
	/**
	 * 
	 * @param url
	 * @return
	 * 
	 * 工具方法，从href中取出id.主要用于DoubanMoive匹配出id来
	 */
	public static String getIdStringFromUrl(String url) {
		/**
		 * 正则将id数据匹配出来
		 */
		Pattern p = Pattern.compile("subject/(\\d+)/");
		Matcher m = p.matcher(url);
		m.find();
		String idStr = m.group(1);
		
		return idStr;
	}
}
