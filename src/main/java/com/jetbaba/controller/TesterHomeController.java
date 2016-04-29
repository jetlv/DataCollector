package com.jetbaba.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jetbaba.fetcher.TesterhomeCrawler;
import com.jetbaba.utils.StringUtils;

public class TesterHomeController extends BaseController {

	/**
	 * visitedMap在磁盘的位置
	 * testerhome爬虫控制器
	 */
	public static String VISITED_PATH = "output/map_testerhome.txt";
	public static Map<String, Boolean> visted;

	/**
	 * 
	 * @param crawlStorageFolder
	 * @param numberOfCrawlers
	 * @param seeds
	 * 
	 * testerHome爬虫 省去一些参数
	 **/ 
	public TesterHomeController(String crawlStorageFolder, Integer numberOfCrawlers, List<String> seeds) {
		super(crawlStorageFolder, numberOfCrawlers, "", "", seeds, TesterhomeCrawler.class);
	}
	
	@Override
	public void initMap() {
		visted = new HashMap<String, Boolean>();

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

}
