package com.jetbaba.handler;


/**
 * 
 * @author jet
 *
 * 处理器工厂类，产品清单：
 * 1. 传入Integer=1，为获取豆瓣电影Handler
 * 2.
 */

public class HandlerFactory {
	
	public static Handler getSpecificHandler(Integer type) { 
		if(type == 1) {
			return new DoubanMovieHandler();
		} else {
			return null;
		}
	}
	
}
