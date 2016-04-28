package com.jetbaba.controller;

import java.util.List;

/**
 * 
 * @author jet
 *
 * douban 控制器
 */
public class DoubanController extends BaseController {
	
	public DoubanController(String crawlStorageFolder, Integer numberOfCrawlers, String userAgent, String cookie,
			List<String> seeds, Class clazz) {
		super(crawlStorageFolder, numberOfCrawlers, userAgent, cookie, seeds, clazz);
	}

	@Override
	public void loadVisitedMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initMap() {
		// TODO Auto-generated method stub
		
	}

}
