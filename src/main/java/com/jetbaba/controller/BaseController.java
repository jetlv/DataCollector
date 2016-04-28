package com.jetbaba.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * 
 * @author jet
 * 
 * crawler4j Controller基类
 *
 */
public abstract class BaseController {
	/**
	 * 结果存储位置
	 */
	protected String crawlStorageFolder;
	/**
	 * 线程数量
	 */
	protected Integer numberOfCrawlers;
	/**
	 * user-agent字符串
	 */
	protected String userAgent;
	/**
	 * Cookie字符串
	 */
	protected String cookie;
	/**
	 * Seed列表
	 */
	protected List<String> seeds;
	
	/**
	 * 执行到crawleclass
	 */
	protected Class clazz;
	
	
	
	public BaseController(String crawlStorageFolder, Integer numberOfCrawlers, String userAgent, String cookie,
			List<String> seeds, Class clazz) {
		super();
		this.crawlStorageFolder = crawlStorageFolder;
		this.numberOfCrawlers = numberOfCrawlers;
		this.userAgent = userAgent;
		this.cookie = cookie;
		this.seeds = seeds;
		this.clazz = clazz;
	}



	public BaseController() {
		super();
	}



	/**
	 * 开始执行
	 * @throws Exception 
	 */
	public void startCrawler() throws Exception {
		/**
		 * 配置config
		 */
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setCrawlStorageFolder(crawlStorageFolder);
        Header header_userAgent = new BasicHeader("User-Agent", this.userAgent);
        Header header_cookie = new BasicHeader("cookie", this.cookie);
        Collection<Header> defaultHeaders = new ArrayList<Header>();
        defaultHeaders.add(header_userAgent);
        defaultHeaders.add(header_cookie);
        config.setDefaultHeaders(defaultHeaders);
        
      /**
       * 初始化Controller组件
       */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
        for(int i =0; i < seeds.size() ; i++) {
        	controller.addSeed(seeds.get(i));
        }
        /**
         * 执行
         */
        controller.start(clazz, numberOfCrawlers);
	}
	
	/**
	 * 加载map
	 */
	public abstract void loadVisitedMap();
	
	/**
	 * 初始化map
	 */
	public abstract void initMap();
}
