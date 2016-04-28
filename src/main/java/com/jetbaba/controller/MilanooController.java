package com.jetbaba.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jetbaba.fetcher.MilanooCrawler;
import com.jetbaba.utils.StringUtils;

public class MilanooController extends BaseController {
	
	/**
	 * visitedMap在磁盘的位置
	 */
	public static String VISITED_PATH = "output/map.txt";
	/**
	 * 访问过的链接存储器 配置文件中如果milanoo.ifloadvisited=1，则要调用方法读取.否则开启全新到爬虫
	 */
	public static Map<String, Boolean> visted;
	
	public MilanooController(String crawlStorageFolder, Integer numberOfCrawlers, String userAgent, String cookie,
			List<String> seeds, Class clazz) {
		super(crawlStorageFolder, numberOfCrawlers, userAgent, cookie, seeds, clazz);
	}
	
	/**
	 * 
	 * @param crawlStorageFolder
	 * @param numberOfCrawlers
	 * @param seeds
	 * 
	 * milanoo爬虫 省去一些参数
	 **/ 
	public MilanooController(String crawlStorageFolder, Integer numberOfCrawlers, List<String> seeds) {
		super(crawlStorageFolder, numberOfCrawlers, "", "", seeds, MilanooCrawler.class);
	}
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * 
	 * 加载已浏览的map
	 */
	public void loadVisitedMap()  {
		File file = new File(VISITED_PATH);
		if(!file.exists()) {
			visted = new HashMap<String, Boolean>();
		} else {
			try {
				visted = StringUtils.getMap(VISITED_PATH);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 仅初始化visited
	 */
	public void initMap() {
		visted = new HashMap<String, Boolean>();
	}
	

}
